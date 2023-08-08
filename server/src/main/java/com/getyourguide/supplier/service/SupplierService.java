package com.getyourguide.supplier.service;

import com.getyourguide.supplier.model.Supplier;
import com.getyourguide.supplier.repository.SupplierRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  private final SupplierRepository supplierRepository;

  public SupplierService(SupplierRepository supplierRepository) {
    this.supplierRepository = supplierRepository;
  }

  public List<Supplier> getAllSuppliers() throws IOException {
    return supplierRepository.getAllSuppliers();
  }

  public Optional<Supplier> getSupplierById(long id) throws IOException {
    return supplierRepository.getSupplierById(id);
  }
}
