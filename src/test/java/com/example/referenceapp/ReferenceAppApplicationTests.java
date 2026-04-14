package com.example.referenceapp;

import com.example.referenceapp.user.User;
import com.example.referenceapp.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ReferenceAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void loginPageShouldBePublic() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"));
    }

    @Test
    void usersShouldRequireAuthentication() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void rootShouldRedirectToUsersForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    void rootShouldRequireAuthentication() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    void usersPageShouldRenderForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/index"));
    }

    @Test
    @WithMockUser
    void deleteUserShouldRedirectForStandardRequest() throws Exception {
        mockMvc.perform(post("/users/1/delete").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    @WithMockUser
    void deleteUserShouldReturnUserSectionForHtmxRequest() throws Exception {
        mockMvc.perform(post("/users/1/delete")
                        .header("HX-Request", "true")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/index :: userSection"));
    }

    @Test
    @WithMockUser
    void userDetailShouldRenderForExistingUser() throws Exception {
        User user = userRepository.save(new User(null, "Detail User", "detail@example.com", null, null));

        mockMvc.perform(get("/users/" + user.id()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/detail"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Todo List")));
    }

    @Test
    @WithMockUser
    void userDetailShouldReturnNotFoundForMissingUser() throws Exception {
        mockMvc.perform(get("/users/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void addTodoShouldRedirectForStandardRequest() throws Exception {
        User user = userRepository.save(new User(null, "Todo User", "todo@example.com", null, null));

        mockMvc.perform(post("/users/" + user.id() + "/todos")
                        .with(csrf())
                        .param("text", "First todo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/" + user.id()));
    }

    @Test
    @WithMockUser
    void addTodoShouldReturnTodoSectionForHtmxRequest() throws Exception {
        User user = userRepository.save(new User(null, "Todo User Htmx", "todo-htmx@example.com", null, null));

        mockMvc.perform(post("/users/" + user.id() + "/todos")
                        .header("HX-Request", "true")
                        .with(csrf())
                        .param("text", "HTMX todo"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/detail :: todoSection"));
    }

    @Test
    @WithMockUser
    void deleteTodoShouldRedirectForStandardRequest() throws Exception {
        User user = userRepository.save(new User(null, "Todo Delete User", "todo-delete@example.com", null, null));
        Long todoId = userRepository.addTodo(user.id(), "Todo to delete").orElseThrow().id();

        mockMvc.perform(post("/users/" + user.id() + "/todos/" + todoId + "/delete")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/" + user.id()));
    }

    @Test
    @WithMockUser
    void deleteTodoShouldReturnTodoSectionForHtmxRequest() throws Exception {
        User user = userRepository.save(new User(null, "Todo Delete Htmx", "todo-delete-htmx@example.com", null, null));
        Long todoId = userRepository.addTodo(user.id(), "Todo to delete with htmx").orElseThrow().id();

        mockMvc.perform(post("/users/" + user.id() + "/todos/" + todoId + "/delete")
                        .header("HX-Request", "true")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/detail :: todoSection"));
    }

    @Test
    @WithMockUser
    void markTodoDoneShouldRedirectForStandardRequest() throws Exception {
        User user = userRepository.save(new User(null, "Todo Done User", "todo-done@example.com", null, null));
        Long todoId = userRepository.addTodo(user.id(), "Todo to complete").orElseThrow().id();

        mockMvc.perform(post("/users/" + user.id() + "/todos/" + todoId + "/done")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/" + user.id()));
    }

    @Test
    @WithMockUser
    void markTodoDoneShouldReturnTodoSectionForHtmxRequest() throws Exception {
        User user = userRepository.save(new User(null, "Todo Done Htmx", "todo-done-htmx@example.com", null, null));
        Long todoId = userRepository.addTodo(user.id(), "Todo to complete with htmx").orElseThrow().id();

        mockMvc.perform(post("/users/" + user.id() + "/todos/" + todoId + "/done")
                        .header("HX-Request", "true")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/detail :: todoSection"));
    }
}

