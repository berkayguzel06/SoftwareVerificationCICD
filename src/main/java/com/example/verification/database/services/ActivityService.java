package com.example.verification.database.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.verification.database.model.Activity;
import com.example.verification.database.repository.ActivityCrud;

@Service
@Transactional
public class ActivityService {
    private final ActivityCrud activityCrud;

    @Autowired
    public ActivityService(ActivityCrud activityCrud){
        this.activityCrud = activityCrud;
    }

    @Transactional(readOnly = true)
    public List<Activity> findAllActivities() {
        Iterable<Activity> activity = activityCrud.findAll();
        List<Activity> activityList = StreamSupport.stream(activity.spliterator(), false).toList();
        return activityList.stream().toList();
    }

    @Transactional(readOnly = true)
    public List<Activity> findActivitiesByUserId(Integer id) {
        List<Activity> activities = activityCrud.findByUserId(id);
        return activities.stream().toList();
    }

    @Transactional(readOnly = true)
    public Activity findActivityById(Integer id) {
        Activity activity = activityCrud.findById(id).orElse(null);
        return activity;
    }

    @Transactional
    public Activity createActivity(Activity activity) {
        activity = activityCrud.save(activity);
        return activity;
    }

    @Transactional
    public Activity updateActivity(Integer id, Activity activity) {
        Activity existingActivity = activityCrud.findById(id).orElse(null);
        existingActivity.setDescription(activity.getDescription());
        existingActivity.setOrganizator(activity.getOrganizator());
        existingActivity.setTitle(activity.getTitle());
        existingActivity.setUser(activity.getUser());
        existingActivity = activityCrud.save(existingActivity);
        return existingActivity;
    }

    @Transactional
    public void deleteActivityById(Integer id) {
        Activity activity = activityCrud.findById(id).orElse(null);
        activityCrud.delete(activity);
    }
}
