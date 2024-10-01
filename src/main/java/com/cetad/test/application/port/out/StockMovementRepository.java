package com.cetad.test.application.port.out;

import com.cetad.test.domain.model.StockMovement;

import java.util.List;
import java.util.Optional;

public interface StockMovementRepository {
    StockMovement save(StockMovement stockMovement);
    List<StockMovement> findByProductId(Long productId);
}
