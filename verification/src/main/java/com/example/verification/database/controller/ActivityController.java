package com.example.verification.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.verification.database.model.Activity;
import com.example.verification.database.services.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping(produces="application/json")
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.findAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Integer id) {
        Activity activity = activityService.findActivityById(id);
        return ResponseEntity.ok(activity);
    }

    @PostMapping(value="/api/auth", consumes="application/json", produces="application/json")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);
        return ResponseEntity.ok(createdActivity);
    }

    @PutMapping(value="/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<Activity> updateActivity(@PathVariable("id") Integer id, @RequestBody Activity activity) {
        Activity updatedActivity = activityService.updateActivity(id, activity);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("id") Integer id) {
        activityService.deleteActivityById(id);
        return ResponseEntity.noContent().build();
    }
}
