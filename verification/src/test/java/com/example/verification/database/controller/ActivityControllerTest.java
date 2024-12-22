package com.example.verification.database.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.verification.database.model.Activity;
import com.example.verification.database.model.User;
import com.example.verification.database.services.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ActivityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private ActivityController activityController;

    @Mock
    private ActivityService activityService;

    @Mock
    private Activity activity;

    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    @Test
    public void testGetAllActivities() throws Exception {
        User user = new User("User 1", "user1@gmail.com", "password", "firstname", "lastname");
        Activity activity1 = new Activity("Activity 1", user);
        Activity activity2 = new Activity("Activity 2", user);
        user.setId(1);
        activity1.setId(1);
        activity2.setId(2);
        
        when(activityService.findAllActivities()).thenReturn(Arrays.asList(activity1, activity2));

        mockMvc.perform(get("/activity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].title").value("Activity 2"));
    }

    @Test
    public void testGetActivityById() throws Exception {
        User user = new User("User 1", "user1@gmail.com", "password", "firstname", "lastname");
        Activity activity = new Activity("Activity 1", user);
        user.setId(1);
        activity.setId(1);
        
        when(activityService.findActivityById(1)).thenReturn(activity);

        mockMvc.perform(get("/activity/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Activity 1"));
    }
  /*
   
  @Test
  public void testCreateActivity() throws Exception {
    User user = new User("User 1", "user1@gmail.com", "password", "firstname", "lastname");
    Activity activity = new Activity("New Activity", user);
    user.setId(1);
    activity.setId(1);
    
    when(activityService.createActivity(any(Activity.class))).thenReturn(activity);
    
    mockMvc.perform(post("/activity")
    .contentType(MediaType.APPLICATION_JSON)
    .content(activity.toString()))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.title").value("New Activity"));
}

@Test
public void testUpdateActivity() throws Exception {
    User user = new User("User 1", "user1@gmail.com", "password", "firstname", "lastname");
    Activity activity = new Activity("Updated Activity", user);
    user.setId(1);
    activity.setId(1);
    when(activityService.updateActivity(anyInt(), any(Activity.class))).thenReturn(activity);
    mockMvc.perform(put("/activity/1")
    .contentType("application/json")
    .content(objectMapper.writeValueAsString(activity)))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.title").value("Updated Activity"));
}

@Test
public void testDeleteActivity() throws Exception {
    mockMvc.perform(delete("/activity/1"))
    .andExpect(status().isNoContent());
    Mockito.verify(activityService).deleteActivityById(1);
}
*/
}
