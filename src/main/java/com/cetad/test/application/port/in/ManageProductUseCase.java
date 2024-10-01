package com.cetad.test.application.port.in;

import com.cetad.test.domain.model.Product;

import java.util.List;

public interface ManageProductUseCase {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product getProductByName(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
