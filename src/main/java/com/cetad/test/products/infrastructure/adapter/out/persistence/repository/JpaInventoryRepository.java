package com.cetad.test.products.infrastructure.adapter.out.persistence.repository;

import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findByIdProduct(Long idProduct);
    boolean existsByIdProduct(Long idProduct);
}
