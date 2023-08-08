package com.getyourguide.supplier.repository;

import com.getyourguide.supplier.model.Supplier;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
  List<Supplier> getAllSuppliers() throws IOException;
  Optional<Supplier> getSupplierById(long id) throws IOException;
}
