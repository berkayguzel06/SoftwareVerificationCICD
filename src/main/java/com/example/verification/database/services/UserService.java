package com.example.verification.database.services;

import org.springframework.stereotype.Service;

import com.example.verification.database.model.User;


@Service
public class UserService {

    public User getUser(Long id) {
        // Mock veri dönecek
        User user = new User();
        user.setId(id);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        return user;
    }

    public User createUser(User user) {
        // Mock veri dönecek
        user.setId(1L);
        return user;
    }
}
