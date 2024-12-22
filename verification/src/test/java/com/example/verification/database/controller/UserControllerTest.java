package com.example.verification.database.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.verification.database.model.User;
import com.example.verification.database.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User("user1", "email1@example.com", "password", "firstname", "lastname");
        User user2 = new User("user2", "email2@example.com", "password", "firstname", "lastname");
        user1.setId(1);
        user2.setId(2);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].username").value("user2"));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        User user = new User("user1", "email1@example.com", "password", "firstname", "lastname");
        user.setId(1);
        when(userService.findUserByUsername("user1")).thenReturn(user);

        mockMvc.perform(get("/user/username/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("user1"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User("user1", "email1@example.com", "password", "firstname", "lastname");
        user.setId(1);
        when(userService.findUserById(1)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("user1"));
    }
/*
 
@Test
public void testCreateUser() throws Exception {
    User user = new User("newuser", "newuser@example.com", "password", "firstname", "lastname");
    user.setId(1);
    
    when(userService.createUser(any(User.class))).thenReturn(user);
    
    mockMvc.perform(post("/user")
    .contentType("application/json")
    .content("{\"username\": \"newuser\", \"email\": \"newuser@example.com\"}"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.username").value("newuser"));
}

@Test
public void testUpdateUser() throws Exception {
    User user = new User("updateduser", "updateduser@example.com", "password", "firstname", "lastname");
    user.setId(1);
    
    when(userService.update(anyInt(), any(User.class))).thenReturn(user);
    
    mockMvc.perform(put("/user/1")
    .contentType("application/json")
    .content("{\"username\": \"updateduser\", \"email\": \"updateduser@example.com\"}"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.username").value("updateduser"));
}
*/

@Test
public void testDeleteUser() throws Exception {
    User user = new User("deleteduser", "deleteduser@example.com", "password", "firstname", "lastname");
    user.setId(1);
        
        when(userService.deleteUser(1)).thenReturn(user);

        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("deleteduser"));
    }
}
