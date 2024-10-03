package com.cetad.test.products.infrastructure.adapter.out.persistence.repository;

import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.StockMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaStockMovementRepository extends JpaRepository<StockMovementEntity, Long> {
    @Query("SELECT sm FROM StockMovementEntity sm WHERE sm.productEntity.id = :idProduct ORDER BY sm.movementDate ASC")
    List<StockMovementEntity> findByIdProductOrderByMovementDateAsc(@Param("idProduct") Long idProduct);
}
