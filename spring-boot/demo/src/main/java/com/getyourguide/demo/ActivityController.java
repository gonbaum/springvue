package com.getyourguide.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActivityController {

  @Autowired
  private ResourceLoader resourceLoader;

  @GetMapping("/debug")
  public void debug(
    @RequestParam(
      name = "title",
      required = false,
      defaultValue = "NONE"
    ) String title,
    Model model
  ) {
    try {
      model.addAttribute("title", title);
      // create ObjectMapper instance
      ObjectMapper objectMapper = new ObjectMapper();
      // read JSON file and convert to a list of activities
      List<Activity> activities = readActivitiesFromJsonFile();
      model.addAttribute("activities", activities);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GetMapping("/activities")
  public ResponseEntity<List<Activity>> activities() {
    try {
      // read JSON file and convert to a list of activities
      List<Activity> activities = readActivitiesFromJsonFile();
      return ResponseEntity.ok(activities);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/activities/{id}")
  public ResponseEntity<Activity> getActivityById(@PathVariable long id) {
    try {
      List<Activity> activities = readActivitiesFromJsonFile();

      // Find the activity with the given ID
      Optional<Activity> matchedActivity = activities
        .stream()
        .filter(activity -> activity.getId() == id)
        .findFirst();

      // Return the activity if found, or a 404 response if not found
      if (matchedActivity.isPresent()) {
        return ResponseEntity.ok(matchedActivity.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/activities/search")
  public ResponseEntity<List<Activity>> searchActivitiesByTitle(
    @RequestParam(name = "title") String title
  ) {
    try {
      List<Activity> activities = readActivitiesFromJsonFile();

      // Filter activities by title
      List<Activity> matchedActivities = activities
        .stream()
        .filter(activity ->
          activity.getTitle().toLowerCase().contains(title.toLowerCase())
        )
        .collect(Collectors.toList());

      return ResponseEntity.ok(matchedActivities);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Activity> readActivitiesFromJsonFile() throws IOException {
    // create ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();
    // read JSON file and convert to a list of activities
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
