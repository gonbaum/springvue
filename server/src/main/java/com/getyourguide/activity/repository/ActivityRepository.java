package com.getyourguide.activity.repository;

import com.getyourguide.activity.model.Activity;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository {
  List<Activity> getAllActivities();
  Optional<Activity> getActivityById(long id);
  List<Activity> searchActivitiesByTitle(String title);
}
