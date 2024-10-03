package com.cetad.test.products.infrastructure.adapter.out.persistence.repository;

import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Long> {
    @Query("SELECT i FROM InventoryEntity i WHERE i.productEntity.id = :id")
    InventoryEntity findByIdProduct(@Param("id") Long id);
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM InventoryEntity i WHERE i.productEntity.id = :id")
    boolean existsByIdProduct(@Param("id") Long id);
}
