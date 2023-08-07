package com.getyourguide.activity.controller;

import com.getyourguide.activity.model.Activity;
import com.getyourguide.activity.service.ActivityService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActivityController {

  @Autowired
  private ActivityService activityService;

  @GetMapping("/activities")
  public ResponseEntity<List<Activity>> activities() {
    List<Activity> activities = activityService.getAllActivities();
    return ResponseEntity.ok(activities);
  }

  @GetMapping("/activities/{id}")
  public ResponseEntity<Activity> getActivityById(@PathVariable long id) {
    Optional<Activity> matchedActivity = activityService.getActivityById(id);
    // Return the activity if found, or a 404 response if not found
    return matchedActivity
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/activities/search")
  public ResponseEntity<List<Activity>> searchActivitiesByTitle(
    @RequestParam(name = "title") String title
  ) {
    List<Activity> matchedActivities = activityService.searchActivitiesByTitle(
      title
    );
    return ResponseEntity.ok(matchedActivities);
  }
}
