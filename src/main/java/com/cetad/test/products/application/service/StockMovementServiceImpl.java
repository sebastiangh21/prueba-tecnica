package com.cetad.test.products.application.service;

import com.cetad.test.products.application.port.in.ManageStockMovementUseCase;
import com.cetad.test.products.application.port.out.StockMovementRepository;
import com.cetad.test.products.domain.model.StockMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockMovementServiceImpl implements ManageStockMovementUseCase {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Override
    public List<StockMovement> getStockMovementForProductId(Long productId) {
        return stockMovementRepository.findByProductId(productId);
    }
}
