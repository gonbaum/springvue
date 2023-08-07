package com.getyourguide.activity.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.activity.model.Activity;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  private final ResourceLoader resourceLoader;

  @Autowired
  public ActivityService(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  public List<Activity> getAllActivities() throws IOException {
    List<Activity> activities = readActivitiesFromJsonFile();
    return activities;
  }

  public Optional<Activity> getActivityById(long id) throws IOException {
    List<Activity> activities = readActivitiesFromJsonFile();
    return activities
      .stream()
      .filter(activity -> activity.getId() == id)
      .findFirst();
  }

  public List<Activity> searchActivitiesByTitle(String title)
    throws IOException {
    List<Activity> activities = readActivitiesFromJsonFile();
    return activities
      .stream()
      .filter(activity ->
        activity.getTitle().toLowerCase().contains(title.toLowerCase())
      )
      .collect(Collectors.toList());
  }

  private List<Activity> readActivitiesFromJsonFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Resource resource = resourceLoader.getResource(
      "classpath:static/activities.json"
    );
    InputStream inputStream = resource.getInputStream();
    return objectMapper.readValue(
      inputStream,
      new TypeReference<List<Activity>>() {}
    );
  }
}
