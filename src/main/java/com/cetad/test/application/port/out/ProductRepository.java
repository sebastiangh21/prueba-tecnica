package com.cetad.test.application.port.out;

import com.cetad.test.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void delete(Long id);
    List<Product> findByName(String name);
    boolean existsById(Long id);
}
