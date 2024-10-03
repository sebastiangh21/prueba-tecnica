package com.cetad.test.products.application.port.in;

import com.cetad.test.products.domain.model.StockMovement;

import java.util.List;

public interface ManageStockMovementUseCase {
    List<StockMovement> getStockMovementForProductId(Long productId);

}
