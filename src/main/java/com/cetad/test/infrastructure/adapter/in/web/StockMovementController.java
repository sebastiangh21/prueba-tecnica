package com.cetad.test.infrastructure.adapter.in.web;

import com.cetad.test.application.port.in.ManageStockMovementUseCase;
import com.cetad.test.domain.model.Product;
import com.cetad.test.domain.model.StockMovement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class StockMovementController {
    private final ManageStockMovementUseCase manageStockMovementUseCase;

    public StockMovementController(ManageStockMovementUseCase manageStockMovementUseCase) {
        this.manageStockMovementUseCase = manageStockMovementUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StockMovement>> getMovements(@PathVariable Long id) {
        return new ResponseEntity<>(manageStockMovementUseCase.getStockMovementForProductId(id), HttpStatus.OK);
    }
}
