package com.example.verification.database.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.verification.database.model.User;
import com.example.verification.database.services.UserService;

@WebMvcTest(UserController.class)  // Correct controller reference
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean  // Use @MockBean to mock the UserService
    private UserService userService;

    @BeforeEach
    void setup() {
        // No need for manual MockMvc setup when using @WebMvcTest
    }

    @Test
    public void testGetUser() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("John Doe");
        mockUser.setEmail("johndoe@example.com");

        when(userService.getUser(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }
}
