package com.cetad.test.products.domain.model;

public class Inventory {
    private Long idInventory;
    private int availableQuantity;
    private  Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Long idInventory) {
        this.idInventory = idInventory;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "Inventory{id=" + idInventory +
                ", idProduct='" + product.getIdProduct() +
                ", availableQuantity='" + availableQuantity +
                '}';
    }
}
