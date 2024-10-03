package com.cetad.test.products.infrastructure.adapter.out.persistence.repository;

import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.StockMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStockMovementRepository extends JpaRepository<StockMovementEntity, Long> {
    List<StockMovementEntity> findByIdProductOrderByMovementDateAsc(Long idProduct);
}
