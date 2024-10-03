package com.cetad.test.products.application.port.out;

import com.cetad.test.products.domain.model.Inventory;

import java.util.Optional;

public interface InventoryRepository {
    Inventory save(Inventory inventory);
    Optional<Inventory> findByProductId(Long productId);
    boolean existsByProductId(Long id);
}
