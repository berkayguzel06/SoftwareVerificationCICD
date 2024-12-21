package com.example.green_code.database.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.green_code.database.CrudRepository;
import com.example.green_code.database.model.File;

public interface FileCrud extends CrudRepository<File, Integer> {
    @Query("SELECT f FROM File f " +
           "JOIN f.User u " +
           "WHERE u.name = :username")
    Optional<Iterable<File>> findByUsername(
            @Param("username") String username
    );
}
