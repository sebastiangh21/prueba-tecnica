package com.cetad.test.products.domain.model;

import java.time.LocalDateTime;

public class StockMovement {
    private Long idMovement;
    private Long idProduct;
    private String movementType;
    private LocalDateTime movementDate;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(Long idMovement) {
        this.idMovement = idMovement;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }

    @Override
    public String toString() {
        return "StockMovement{" +
                "idMovement=" + idMovement +
                ", idProduct=" + idProduct +
                ", movementType='" + movementType + '\'' +
                ", movementDate=" + movementDate +
                ", quantity=" + quantity +
                '}';
    }
}
