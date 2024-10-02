package com.cetad.test.application.port.in;

import com.cetad.test.domain.model.StockMovement;

import java.util.List;

public interface ManageStockMovementUseCase {
    List<StockMovement> getStockMovementForProductId(Long productId);
}
