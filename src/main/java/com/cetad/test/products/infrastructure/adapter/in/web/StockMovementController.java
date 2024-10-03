package com.cetad.test.products.infrastructure.adapter.in.web;

import com.cetad.test.products.application.port.in.ManageStockMovementUseCase;
import com.cetad.test.products.domain.model.StockMovement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(StockMovementController.class);
    @Autowired
    private ManageStockMovementUseCase manageStockMovementUseCase;

    @GetMapping("/{id}")
    @Operation(
            summary = "Get stock movements for a product",
            description = "Retrieve a list of all stock movements associated with a specific product ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Stock movements retrieved successfully",
                            content = @Content(schema = @Schema(implementation = StockMovement.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<List<StockMovement>> getMovements(@PathVariable Long id) {
        logger.info("Fetching stock movements for product ID: {}", id);
        try {
            List<StockMovement> movements = manageStockMovementUseCase.getStockMovementForProductId(id);
            logger.info("Stock movements found for product ID {}: {}", id, movements);
            return new ResponseEntity<>(movements, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching stock movements for product ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
