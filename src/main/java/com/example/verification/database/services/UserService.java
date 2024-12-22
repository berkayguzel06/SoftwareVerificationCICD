package com.example.verification.database.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.verification.database.model.User;
import com.example.verification.database.repository.UserCrud;


@Service
public class UserService {

    private final UserCrud userCrud;

    @Autowired
    public UserService(UserCrud userCrud){
        this.userCrud = userCrud;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        Iterable<User> userIterable = userCrud.findAll();
        List<User> users = StreamSupport.stream(userIterable.spliterator(), false)
                                                            .toList();
        return users.stream().toList();
    }

    @Transactional(readOnly = true)
    public User findUserById(Integer id){
        User user = userCrud.findById(id).orElse(null);
        return user;
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username){
        User user = userCrud.findByUsername(username);
        return user;
    }

    @Transactional
    public User createUser(User user){
        User savedUser = userCrud.save(user);
        return savedUser;
    }

    @Transactional
    public User update(Integer id, User user) {
        User existingUser = userCrud.findById(id).orElse(null);
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setUsername(user.getUsername());
        User savedUser = userCrud.save(existingUser);
        return savedUser;
    }

    @Transactional
    public User deleteUser(Integer id){
        User existing = userCrud.findById(id).orElse(null);
        userCrud.delete(existing);
        return existing;
    }
}
