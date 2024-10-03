package com.cetad.test.products.infrastructure.adapter.out.persistence;

import com.cetad.test.products.application.port.out.StockMovementRepository;
import com.cetad.test.products.domain.model.StockMovement;
import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.StockMovementEntity;
import com.cetad.test.products.infrastructure.adapter.out.persistence.mapper.StockMovementMapper;
import com.cetad.test.products.infrastructure.adapter.out.persistence.repository.JpaStockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockMovementPersistenceAdapter implements StockMovementRepository {

    @Autowired
    private JpaStockMovementRepository jpaStockMovementRepository;
    @Autowired
    private StockMovementMapper mapper;

    @Override
    public StockMovement save(StockMovement stockMovement) {
        StockMovementEntity stockMovementEntity = mapper.toStockMovementEntity(stockMovement);
        return mapper.toStockMovement(jpaStockMovementRepository.save(stockMovementEntity));
    }

    @Override
    public List<StockMovement> findByProductId(Long productId) {
        List<StockMovementEntity> stockMovementEntities = jpaStockMovementRepository.findByIdProductOrderByMovementDateAsc(productId);
        return mapper.toStockMovements(stockMovementEntities);
    }
}
