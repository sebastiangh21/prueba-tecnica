package com.cetad.test.application.port.out;

import com.cetad.test.domain.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository {
    Inventory save(Inventory inventory);
    Optional<Inventory> findByProductId(Long productId);
    void updateQuantity(Long productId, int quantity);
}
