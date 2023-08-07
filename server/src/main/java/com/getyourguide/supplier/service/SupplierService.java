package com.getyourguide.supplier.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.supplier.model.Supplier;
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
public class SupplierService {

  private final ResourceLoader resourceLoader;

  @Autowired
  public SupplierService(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  public List<Supplier> getAllSuppliers() throws IOException {
    List<Supplier> suppliers = readSuppliersFromJsonFile();
    return suppliers;
  }

  public Optional<Supplier> getSupplierById(long id) throws IOException {
    List<Supplier> suppliers = readSuppliersFromJsonFile();
    return suppliers
      .stream()
      .filter(supplier -> supplier.getId() == id)
      .findFirst();
  }

  private List<Supplier> readSuppliersFromJsonFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Resource resource = resourceLoader.getResource(
      "classpath:static/suppliers.json"
    );
    InputStream inputStream = resource.getInputStream();
    return objectMapper.readValue(
      inputStream,
      new TypeReference<List<Supplier>>() {}
    );
  }
}
