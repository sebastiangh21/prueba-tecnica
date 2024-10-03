package com.cetad.test.products.application.service;

import com.cetad.test.products.application.port.in.ManageInventoryUseCase;
import com.cetad.test.products.application.port.out.InventoryRepository;
import com.cetad.test.products.application.port.out.StockMovementRepository;
import com.cetad.test.products.domain.exception.InsufficientInventoryException;
import com.cetad.test.products.domain.exception.InventoryNotFoundForProductIdException;
import com.cetad.test.products.domain.model.Inventory;
import com.cetad.test.products.domain.model.StockMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryServiceImpl implements ManageInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Override
    public Inventory getInventoryForProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundForProductIdException("Inventory not found for product id: " + productId));
    }

    @Override
    public void incrementInventoryByOne(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundForProductIdException("Inventory not found for product id: " + productId));
        int quantity = inventory.getAvailableQuantity();
        inventory.setAvailableQuantity(quantity + 1);
        inventoryRepository.save(inventory);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setIdProduct(productId);
        stockMovement.setMovementType("entry");
        stockMovement.setQuantity(1);
        stockMovementRepository.save(stockMovement);
    }

    @Override
    public void decrementInventoryByOne(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundForProductIdException("Inventory not found for product id: " + productId));
        int quantity = inventory.getAvailableQuantity();
        if(quantity < 1){
            throw new InsufficientInventoryException("No inventory available for product id: " + productId);
        }
        inventory.setAvailableQuantity(quantity - 1);
        inventoryRepository.save(inventory);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setIdProduct(productId);
        stockMovement.setMovementType("exit");
        stockMovement.setQuantity(1);
        stockMovementRepository.save(stockMovement);
    }
}
