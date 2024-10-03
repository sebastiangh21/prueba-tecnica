package com.cetad.test.products.application.port.in;

import com.cetad.test.products.domain.model.Product;

import java.util.List;

public interface ManageProductUseCase {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductByName(String name);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
