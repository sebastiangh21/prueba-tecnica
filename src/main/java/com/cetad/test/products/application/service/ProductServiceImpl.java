package com.cetad.test.products.application.service;

import com.cetad.test.products.application.port.in.ManageProductUseCase;
import com.cetad.test.products.application.port.out.InventoryRepository;
import com.cetad.test.products.application.port.out.ProductRepository;
import com.cetad.test.products.domain.exception.ProductNotFoundException;
import com.cetad.test.products.domain.model.Inventory;
import com.cetad.test.products.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ManageProductUseCase {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        Inventory newInventory = new Inventory();
        newInventory.setIdProduct(savedProduct.getIdProduct());
        newInventory.setAvailableQuantity(0);
        inventoryRepository.save(newInventory);
        return savedProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        } else {
            productRepository.delete(id);
        }
    }
}
