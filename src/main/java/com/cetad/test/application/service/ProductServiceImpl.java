package com.cetad.test.application.service;

import com.cetad.test.application.port.in.ManageProductUseCase;
import com.cetad.test.application.port.out.InventoryRepository;
import com.cetad.test.application.port.out.ProductRepository;
import com.cetad.test.domain.exception.ProductNotFoundException;
import com.cetad.test.domain.model.Inventory;
import com.cetad.test.domain.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ManageProductUseCase {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

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
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        product.setIdProduct(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.delete(id);
    }
}
