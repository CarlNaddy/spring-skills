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
        User user = userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        model.addAttribute("user", user);
        return "users/detail";
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

    private boolean isHtmx(String htmxRequest) {
        return "true".equalsIgnoreCase(htmxRequest);
    }
}

