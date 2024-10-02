package com.cetad.test.application.port.out;

import com.cetad.test.domain.model.Inventory;

import java.util.Optional;

public interface InventoryRepository {
    Inventory save(Inventory inventory);
    Optional<Inventory> findByProductId(Long productId);
    boolean existsByProductId(Long id);
}
