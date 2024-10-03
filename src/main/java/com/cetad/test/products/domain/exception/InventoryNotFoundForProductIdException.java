package com.cetad.test.products.domain.exception;

public class InventoryNotFoundForProductIdException extends RuntimeException {
    public InventoryNotFoundForProductIdException(String message) {
        super(message);
    }
}
