package com.example.verification.database.repository;
import com.example.verification.database.CrudRepository;
import com.example.verification.database.model.User;
public interface UserCrud extends CrudRepository<User, Integer> {
    <Optional> User findByUsername(String username);
}