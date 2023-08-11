package com.getyourguide.activity.controller;

import com.getyourguide.activity.model.Activity;
import com.getyourguide.activity.service.ActivityService;
import com.getyourguide.util.error.ErrorResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    try {
      List<Activity> activities = activityService.getAllActivities();
      return ResponseEntity.ok(activities);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/activities/{id}")
  public ResponseEntity<?> getActivityById(@PathVariable long id) {
    try {
      Optional<Activity> matchedActivity = activityService.getActivityById(id);

      if (matchedActivity.isPresent()) {
        return ResponseEntity.ok(matchedActivity.get());
      } else {
        ErrorResponse errorResponse = new ErrorResponse(
          "Activity not found",
          HttpStatus.NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/activities/search")
  public ResponseEntity<List<Activity>> searchActivitiesByTitle(
    @RequestParam(name = "title") String title
  ) {
    try {
      List<Activity> matchedActivities = activityService.searchActivitiesByTitle(
        title
      );
      return ResponseEntity.ok(matchedActivities);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
