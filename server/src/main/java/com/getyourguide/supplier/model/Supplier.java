package com.getyourguide.supplier.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

  private Long id;
  private String name;
  private String address;
  private String zip;
  private String city;
  private String country;
}
