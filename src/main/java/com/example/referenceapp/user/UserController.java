package com.example.referenceapp.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @GetMapping("/users/list")
    public String usersList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list :: userListContainer";
    }

    @GetMapping("/users/{id}")
    public String userDetail(@PathVariable("id") Long id, Model model) {
        User user = getRequiredUser(id);
        populateUserDetailModel(model, user);
        return "users/detail";
    }

    @PostMapping("/users/{id}/todos")
    public String addTodo(@PathVariable("id") Long id,
                          @RequestHeader(value = "HX-Request", required = false) String htmxRequest,
                          @RequestParam(value = "text", required = false) String text,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        User user = getRequiredUser(id);
        boolean created = userService.addTodo(id, text).isPresent();

        if (created) {
            String message = "Todo added.";
            redirectAttributes.addFlashAttribute("successMessage", message);
            model.addAttribute("successMessage", message);
        } else {
            String error = "Todo text is required.";
            redirectAttributes.addFlashAttribute("todoError", error);
            model.addAttribute("todoError", error);
        }

        if (isHtmx(htmxRequest)) {
            populateUserDetailModel(model, user);
            return "users/detail :: todoSection";
        }
        return "redirect:/users/{id}";
    }

    @PostMapping("/users/{id}/todos/{todoId}/delete")
    public String deleteTodo(@PathVariable("id") Long id,
                             @PathVariable("todoId") Long todoId,
                             @RequestHeader(value = "HX-Request", required = false) String htmxRequest,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        User user = getRequiredUser(id);
        boolean removed = userService.deleteTodoById(id, todoId);
        String message = removed ? "Todo deleted." : "Todo was already removed.";

        redirectAttributes.addFlashAttribute("successMessage", message);
        model.addAttribute("successMessage", message);

        if (isHtmx(htmxRequest)) {
            populateUserDetailModel(model, user);
            return "users/detail :: todoSection";
        }
        return "redirect:/users/{id}";
    }

    @PostMapping("/users/{id}/todos/{todoId}/done")
    public String markTodoDone(@PathVariable("id") Long id,
                               @PathVariable("todoId") Long todoId,
                               @RequestHeader(value = "HX-Request", required = false) String htmxRequest,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        User user = getRequiredUser(id);
        boolean marked = userService.markTodoDone(id, todoId);
        String message = marked ? "Todo marked as done." : "Todo is already done or missing.";

        redirectAttributes.addFlashAttribute("successMessage", message);
        model.addAttribute("successMessage", message);

        if (isHtmx(htmxRequest)) {
            populateUserDetailModel(model, user);
            return "users/detail :: todoSection";
        }
        return "redirect:/users/{id}";
    }

    @GetMapping("/users/form")
    public String usersForm(Model model) {
        if (!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", new UserForm());
        }
        return "users/form :: userForm";
    }

    @PostMapping("/users")
    public String createUser(@Valid @ModelAttribute("userForm") UserForm userForm,
                             BindingResult bindingResult,
                             @RequestHeader(value = "HX-Request", required = false) String htmxRequest,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        model.addAttribute("users", userService.findAll());
        if (bindingResult.hasErrors()) {
            return isHtmx(htmxRequest) ? "users/form :: userForm" : "users/index";
        }

        userService.create(userForm);
        redirectAttributes.addFlashAttribute("successMessage", "User created successfully.");

        if (isHtmx(htmxRequest)) {
            model.addAttribute("userForm", new UserForm());
            model.addAttribute("users", userService.findAll());
            model.addAttribute("successMessage", "User created successfully.");
            return "users/index :: userSection";
        }
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestHeader(value = "HX-Request", required = false) String htmxRequest,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        boolean removed = userService.deleteById(id);
        String message = removed ? "User deleted successfully." : "User was already removed.";

        redirectAttributes.addFlashAttribute("successMessage", message);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("successMessage", message);

        if (isHtmx(htmxRequest)) {
            return "users/index :: userSection";
        }
        return "redirect:/users";
    }

    @ModelAttribute("userForm")
    public UserForm userForm() {
        return new UserForm();
    }

    private User getRequiredUser(Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private void populateUserDetailModel(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("todos", userService.findTodosByUserId(user.id()));
    }

    private boolean isHtmx(String htmxRequest) {
        return "true".equalsIgnoreCase(htmxRequest);
    }
}

