package com.cetad.test.application.service;

import com.cetad.test.application.port.in.ManageStockMovementUseCase;
import com.cetad.test.application.port.out.StockMovementRepository;
import com.cetad.test.domain.model.StockMovement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockMovementServiceImpl implements ManageStockMovementUseCase {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementServiceImpl(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public List<StockMovement> getStockMovementForProductId(Long productId) {
        return stockMovementRepository.findByProductId(productId);
    }
}
