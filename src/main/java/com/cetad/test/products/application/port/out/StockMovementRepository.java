package com.cetad.test.products.application.port.out;

import com.cetad.test.products.domain.model.StockMovement;

import java.util.List;

public interface StockMovementRepository {
    StockMovement save(StockMovement stockMovement);
    List<StockMovement> findByProductId(Long productId);
}
