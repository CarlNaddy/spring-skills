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
                .andExpect(view().name("users/detail"));
    }

    @Test
    @WithMockUser
    void userDetailShouldReturnNotFoundForMissingUser() throws Exception {
        mockMvc.perform(get("/users/999999"))
                .andExpect(status().isNotFound());
    }
}

