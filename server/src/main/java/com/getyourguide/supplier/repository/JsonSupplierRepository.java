package com.getyourguide.supplier.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.supplier.model.Supplier;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

@Repository
public class JsonSupplierRepository implements SupplierRepository {

  private final ResourceLoader resourceLoader;

  public JsonSupplierRepository(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @Override
  public List<Supplier> getAllSuppliers() throws IOException {
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

  @Override
  public Optional<Supplier> getSupplierById(long id) throws IOException {
    List<Supplier> suppliers = getAllSuppliers();
    return suppliers
      .stream()
      .filter(supplier -> supplier.getId() == id)
      .findFirst();
  }
}
