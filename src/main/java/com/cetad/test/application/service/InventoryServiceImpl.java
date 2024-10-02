package com.cetad.test.application.service;

import com.cetad.test.application.port.in.ManageInventoryUseCase;
import com.cetad.test.application.port.out.InventoryRepository;
import com.cetad.test.application.port.out.StockMovementRepository;
import com.cetad.test.domain.exception.InsufficientInventoryException;
import com.cetad.test.domain.exception.InventoryNotFoundException;
import com.cetad.test.domain.model.Inventory;
import com.cetad.test.domain.model.StockMovement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryServiceImpl implements ManageInventoryUseCase {

    private final InventoryRepository inventoryRepository;
    private final StockMovementRepository stockMovementRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, StockMovementRepository stockMovementRepository) {
        this.inventoryRepository = inventoryRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public Inventory getInventoryForProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product id: " + productId));
    }

    @Override
    public void incrementInventoryByOne(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product id: " + productId));
        int quantity = inventory.getAvailableQuantity();
        inventory.setAvailableQuantity(quantity + 1);
        inventoryRepository.save(inventory);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setIdProduct(productId);
        stockMovement.setMovementType("entry");
        stockMovementRepository.save(stockMovement);
    }

    @Override
    public void decrementInventoryByOne(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product id: " + productId));
        int quantity = inventory.getAvailableQuantity();
        if(quantity < 1){
            throw new InsufficientInventoryException("No inventory available for product id: " + productId);
        }
        inventory.setAvailableQuantity(quantity - 1);
        inventoryRepository.save(inventory);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setIdProduct(productId);
        stockMovement.setMovementType("exit");
        stockMovementRepository.save(stockMovement);
    }
}
