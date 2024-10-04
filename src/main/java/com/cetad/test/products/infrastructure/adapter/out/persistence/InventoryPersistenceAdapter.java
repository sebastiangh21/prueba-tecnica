package com.cetad.test.products.infrastructure.adapter.out.persistence;

import com.cetad.test.products.application.port.out.InventoryRepository;
import com.cetad.test.products.domain.model.Inventory;
import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.InventoryEntity;
import com.cetad.test.products.infrastructure.adapter.out.persistence.mapper.InventoryMapper;
import com.cetad.test.products.infrastructure.adapter.out.persistence.repository.JpaInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InventoryPersistenceAdapter implements InventoryRepository {
    @Autowired
    private JpaInventoryRepository jpaInventoryRepository;
    @Autowired
    private InventoryMapper mapper;

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity inventoryEntity = mapper.toInventoryEntity(inventory);
        return mapper.toInventory(jpaInventoryRepository.save(inventoryEntity));
    }

    @Override
    public Optional<Inventory> findByProductId(Long productId) {
        InventoryEntity inventoryEntity = jpaInventoryRepository.findByIdProduct(productId);
        return Optional.of(mapper.toInventory(inventoryEntity));
    }

    @Override
    public boolean existsByProductId(Long id) {
        return jpaInventoryRepository.existsByIdProduct(id);
    }
}
