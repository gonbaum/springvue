package com.getyourguide.supplier.controller;

import com.getyourguide.supplier.model.Supplier;
import com.getyourguide.supplier.service.SupplierService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @GetMapping("/suppliers")
  public ResponseEntity<List<Supplier>> suppliers() {
    try {
      List<Supplier> suppliers = supplierService.getAllSuppliers();
      return ResponseEntity.ok(suppliers);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/suppliers/{id}")
  public ResponseEntity<Supplier> getSupplierById(@PathVariable long id) {
    try {
      Optional<Supplier> matchedSupplier = supplierService.getSupplierById(id);

      // Return the controller if found, or a 404 response if not found
      if (matchedSupplier.isPresent()) {
        return ResponseEntity.ok(matchedSupplier.get());
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
