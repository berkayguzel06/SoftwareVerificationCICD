package com.example.green_code.database.repository;
import com.example.green_code.database.CrudRepository;
import com.example.green_code.database.model.User;
public interface UserCrud extends CrudRepository<User, Integer> {
    <Optional> User findByUsername(String username);
}