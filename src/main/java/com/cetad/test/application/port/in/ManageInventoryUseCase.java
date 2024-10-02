package com.cetad.test.application.port.in;

import com.cetad.test.domain.model.Inventory;

import java.util.Optional;

public interface ManageInventoryUseCase {
    Inventory getInventoryForProductId(Long productId);
    void incrementInventoryByOne(Long productId);
    void decrementInventoryByOne(Long productId);
}
