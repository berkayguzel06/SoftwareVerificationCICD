package com.example.verification.database.repository;

import java.util.List;

import com.example.verification.database.CrudRepository;
import com.example.verification.database.model.Activity;

public interface ActivityCrud extends CrudRepository<Activity, Integer> {
    List<Activity> findByUserId(Integer userId);
}