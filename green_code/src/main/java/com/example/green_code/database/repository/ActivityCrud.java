package com.example.green_code.database.repository;

import java.util.List;

import com.example.green_code.database.CrudRepository;
import com.example.green_code.database.model.Activity;

public interface ActivityCrud extends CrudRepository<Activity, Integer> {
    List<Activity> findByUsersId(Integer userId);
}