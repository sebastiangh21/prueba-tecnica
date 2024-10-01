package com.cetad.test.application.port.in;

import com.cetad.test.domain.model.Inventory;

public interface ManageInventoryUseCase {
    Inventory getInventoryForProductId(Long productId);
    Inventory createInventory(Inventory inventory);
    void updateInventoryQuantity(Long productId, int quantity);
}
