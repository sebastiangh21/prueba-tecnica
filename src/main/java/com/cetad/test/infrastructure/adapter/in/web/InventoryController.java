package com.cetad.test.infrastructure.adapter.in.web;

import com.cetad.test.application.port.in.ManageInventoryUseCase;
import com.cetad.test.domain.model.Inventory;
import com.cetad.test.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final ManageInventoryUseCase manageInventoryUseCase;

    public InventoryController(ManageInventoryUseCase manageInventoryUseCase) {
        this.manageInventoryUseCase = manageInventoryUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable Long id){
        return new ResponseEntity<>(manageInventoryUseCase.getInventoryForProductId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/increment")
    public ResponseEntity<Void> incrementInventory(@PathVariable Long id) {
        manageInventoryUseCase.incrementInventoryByOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/decrement")
    public ResponseEntity<Void> decrementInventory(@PathVariable Long id) {
        manageInventoryUseCase.decrementInventoryByOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
