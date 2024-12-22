package com.example.verification.database.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.verification.database.model.User;

public class UserServiceTest {

    private UserService userService;
    private User mockUser;


    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("John Doe");
        mockUser.setEmail("johnDoe@gmail.com");

        when(userService.getUser(1L)).thenReturn(mockUser);
    }

    @Test
    public void testGetUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("John Doe");
        mockUser.setEmail("johndoe@example.com");

        when(userService.getUser(1L)).thenReturn(mockUser);

        User foundUser = userService.getUser(1L);

        assertEquals("John Doe", foundUser.getName());
        assertEquals("johndoe@example.com", foundUser.getEmail());
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("janedoe@example.com");

        when(userService.createUser(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals("Jane Doe", createdUser.getName());
        assertEquals("janedoe@example.com", createdUser.getEmail());
    }
}
