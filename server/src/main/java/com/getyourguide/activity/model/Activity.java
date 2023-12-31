package com.getyourguide.activity.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

  private Long id;
  private String title;
  private int price;
  private String currency;
  private double rating;
  private boolean specialOffer;
  private Long supplierId;
  private String supplierName;
}
