package com.cetad.test.products.infrastructure.adapter.out.persistence.repository;

import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameOrderByName(String name);
}
