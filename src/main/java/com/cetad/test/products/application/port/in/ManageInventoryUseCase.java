package com.cetad.test.products.application.port.in;

import com.cetad.test.products.domain.model.Inventory;

public interface ManageInventoryUseCase {
    Inventory getInventoryForProductId(Long productId);
    void incrementInventoryByOne(Long productId);
    void decrementInventoryByOne(Long productId);
}
