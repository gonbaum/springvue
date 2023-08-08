package com.getyourguide.activity.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.activity.model.Activity;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

@Repository("jsonActivityRepository")
public class JsonActivityRepository implements ActivityRepository {

  private final ResourceLoader resourceLoader;

  public JsonActivityRepository(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @Override
  public List<Activity> getAllActivities() {
    return readActivitiesFromJsonFile();
  }

  @Override
  public Optional<Activity> getActivityById(long id) {
    return readActivitiesFromJsonFile()
      .stream()
      .filter(activity -> activity.getId() == id)
      .findFirst();
  }

  @Override
  public List<Activity> searchActivitiesByTitle(String title) {
    return getAllActivities()
      .stream()
      .filter(activity ->
        activity.getTitle().toLowerCase().contains(title.toLowerCase())
      )
      .toList();
  }

  private List<Activity> readActivitiesFromJsonFile() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Resource resource = resourceLoader.getResource(
        "classpath:static/activities.json"
      );
      InputStream inputStream = resource.getInputStream();
      return objectMapper.readValue(
        inputStream,
        new TypeReference<List<Activity>>() {}
      );
    } catch (IOException e) {
      throw new RuntimeException("Error while processing activities.", e);
    }
  }
}
