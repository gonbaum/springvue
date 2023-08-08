package com.getyourguide.activity.service;

import com.getyourguide.activity.model.Activity;
import com.getyourguide.activity.repository.JsonActivityRepository;
import com.getyourguide.supplier.model.Supplier;
import com.getyourguide.supplier.service.SupplierService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  private final JsonActivityRepository activityRepository;
  private final SupplierService supplierService;

  public ActivityService(
    JsonActivityRepository activityRepository,
    SupplierService supplierService
  ) {
    this.activityRepository = activityRepository;
    this.supplierService = supplierService;
  }

  public List<Activity> getAllActivities() {
    List<Activity> activities = activityRepository.getAllActivities();

    // Compute supplier name for each activity
    for (Activity activity : activities) {
      computeSupplierName(activity);
    }

    return activities;
  }

  public Optional<Activity> getActivityById(long id) {
    Optional<Activity> optionalActivity = activityRepository.getActivityById(
      id
    );

    optionalActivity.ifPresent(this::computeSupplierName);

    return optionalActivity;
  }

  public List<Activity> searchActivitiesByTitle(String title) {
    List<Activity> activities = getAllActivities();

    return activities
      .stream()
      .filter(activity ->
        activity.getTitle().toLowerCase().contains(title.toLowerCase())
      )
      .toList();
  }

  private void computeSupplierName(Activity activity) {
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
}
