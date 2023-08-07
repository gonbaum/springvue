package com.getyourguide.activity.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.activity.model.Activity;
import com.getyourguide.supplier.model.Supplier;
import com.getyourguide.supplier.service.SupplierService;
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
  private final SupplierService supplierService;

  @Autowired
  public ActivityService(
    ResourceLoader resourceLoader,
    SupplierService supplierService
  ) {
    this.resourceLoader = resourceLoader;
    this.supplierService = supplierService;
  }

  public List<Activity> getAllActivities() {
    List<Activity> activities = readActivitiesFromJsonFile();
    for (Activity activity : activities) {
      try {
        String supplierName = supplierService
          .getSupplierById(activity.getSupplierId())
          .map(Supplier::getName)
          .orElse(null);
        activity.setSupplierName(supplierName);
      } catch (IOException e) {
        throw new RuntimeException("Error while processing activities.", e);
      }
    }
    return activities;
  }

  public Optional<Activity> getActivityById(long id) {
    List<Activity> activities = readActivitiesFromJsonFile();
    Optional<Activity> optionalActivity = activities
      .stream()
      .filter(activity -> activity.getId() == id)
      .findFirst();

    optionalActivity.ifPresent(activity -> {
      try {
        Optional<Supplier> supplier = supplierService.getSupplierById(
          activity.getSupplierId()
        );
        String supplierName = supplier.map(Supplier::getName).orElse(null);
        activity.setSupplierName(supplierName);
      } catch (IOException e) {
        throw new RuntimeException("Error while processing activities.", e);
      }
    });

    return optionalActivity;
  }

  public List<Activity> searchActivitiesByTitle(String title) {
    List<Activity> activities = readActivitiesFromJsonFile();

    for (Activity activity : activities) {
      try {
        Optional<Supplier> supplier = supplierService.getSupplierById(
          activity.getSupplierId()
        );
        String supplierName = supplier.map(Supplier::getName).orElse(null);
        activity.setSupplierName(supplierName);
      } catch (IOException e) {
        throw new RuntimeException("Error while processing activities.", e);
      }
    }

    return activities
      .stream()
      .filter(activity ->
        activity.getTitle().toLowerCase().contains(title.toLowerCase())
      )
      .collect(Collectors.toList());
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
