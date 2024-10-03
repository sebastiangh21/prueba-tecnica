package com.cetad.test.products.infrastructure.adapter.in.web;

import com.cetad.test.products.application.port.in.ManageInventoryUseCase;
import com.cetad.test.products.domain.exception.InsufficientInventoryException;
import com.cetad.test.products.domain.exception.InventoryNotFoundForProductIdException;
import com.cetad.test.products.domain.model.Inventory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    @Autowired
    private ManageInventoryUseCase manageInventoryUseCase;

    @GetMapping("/{id}")
    @Operation(
            summary = "Get inventory for a product",
            description = "Retrieve the inventory details for a specific product using its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inventory found",
                            content = @Content(schema = @Schema(implementation = Inventory.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Inventory not found for the given product ID")
            }
    )
    public ResponseEntity<Inventory> getInventory(@PathVariable Long id) {
        logger.info("Fetching inventory for product ID: {}", id);
        try {
            Inventory inventory = manageInventoryUseCase.getInventoryForProductId(id);
            logger.info("Inventory found for product ID {}: {}", id, inventory);
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (InventoryNotFoundForProductIdException i) {
            logger.error("Error fetching inventory for product ID: {}", id, i);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/increment")
    @Operation(
            summary = "Increment inventory by one",
            description = "Increase the available quantity in inventory by one for a specific product ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Inventory incremented successfully"),
                    @ApiResponse(responseCode = "404", description = "Inventory not found for the given product ID")
            }
    )
    public ResponseEntity<Void> incrementInventory(@PathVariable Long id) {
        logger.info("Incrementing inventory for product ID: {}", id);
        try {
            manageInventoryUseCase.incrementInventoryByOne(id);
            logger.info("Successfully incremented inventory for product ID: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InventoryNotFoundForProductIdException i) {
            logger.error("Error fetching inventory for product ID: {}", id, i);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/decrement")
    @Operation(
            summary = "Decrement inventory by one",
            description = "Decrease the available quantity in inventory by one for a specific product ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Inventory decremented successfully"),
                    @ApiResponse(responseCode = "404", description = "Inventory not found for the given product ID"),
                    @ApiResponse(responseCode = "400", description = "Cannot decrement inventory below zero")
            }
    )
    public ResponseEntity<Void> decrementInventory(@PathVariable Long id) {
        logger.info("Decrementing inventory for product ID: {}", id);
        try {
            manageInventoryUseCase.decrementInventoryByOne(id);
            logger.info("Successfully decremented inventory for product ID: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InventoryNotFoundForProductIdException i) {
            logger.error("Error fetching inventory for product ID: {}", id, i);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InsufficientInventoryException i) {
            logger.error("Error for decrement inventory for product ID: {}", id, i);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
