package com.cetad.test.products.application.service;

import com.cetad.test.products.application.port.out.InventoryRepository;
import com.cetad.test.products.application.port.out.ProductRepository;
import com.cetad.test.products.domain.exception.ProductNotFoundException;
import com.cetad.test.products.domain.model.Inventory;
import com.cetad.test.products.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = Arrays.asList(
                new Product(1L, "Laptop", "High-performance laptop", new BigDecimal("1200.00"), LocalDateTime.now(), LocalDateTime.now()),
                new Product(2L, "Smartphone", "Latest model smartphone", new BigDecimal("800.00"), LocalDateTime.now(), LocalDateTime.now()),
                new Product(3L, "Headphones", "Noise-cancelling headphones", new BigDecimal("300.00"), LocalDateTime.now(), LocalDateTime.now())
        );
    }

    @Test
    void createProduct() {
        Product newProduct = new Product(null, "Tablet", "10-inch tablet", new BigDecimal("500.00"), null, null);
        Product savedProduct = new Product(4L, "Tablet", "10-inch tablet", new BigDecimal("500.00"), LocalDateTime.now(), LocalDateTime.now());
        Inventory newInventory = new Inventory();
        newInventory.setProduct(savedProduct);
        newInventory.setAvailableQuantity(0);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(newInventory);

        Product result = productServiceImpl.createProduct(newProduct);

        assertNotNull(result);
        assertEquals(4L, result.getIdProduct());
        assertEquals("Tablet", result.getName());

        verify(productRepository).save(any(Product.class));
        verify(inventoryRepository).save(any(Inventory.class));
        verify(inventoryRepository, never()).existsByProductId(any(Long.class));
    }

    @Test
    void getProductById() {
        Product product = products.get(0);
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        Product result = productServiceImpl.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdProduct());
        assertEquals("Laptop", result.getName());

        verify(productRepository).findById(1L);
    }

    @Test
    void getProductById_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productServiceImpl.getProductById(1L);
        });

        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(productRepository).findById(1L);
    }


    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productServiceImpl.getAllProducts();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void getProductByName() {
        when(productRepository.findByName("Smartphone")).thenReturn(List.of(products.get(1)));

        List<Product> results = productServiceImpl.getProductByName("Smartphone");

        assertNotNull(results);
        assertEquals("Smartphone", results.get(0).getName());
        assertEquals("Latest model smartphone", results.get(0).getDescription());

        verify(productRepository).findByName("Smartphone");
    }

    @Test
    void updateProduct() {
        Product existingProduct = products.get(0);
        Product updatedProduct = new Product(1L, "Updated Laptop", "Updated description", new BigDecimal("1300.00"), LocalDateTime.now(), LocalDateTime.now());

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productServiceImpl.updateProduct(1L, updatedProduct);

        assertNotNull(result);
        assertEquals(1L, result.getIdProduct());
        assertEquals("Updated Laptop", result.getName());

        verify(productRepository).save(any(Product.class));
        verify(productRepository).findById(1L);
    }

    @Test
    void updateProduct_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productServiceImpl.updateProduct(1L, new Product(
                    0L,
                    "",
                    "",
                    new BigDecimal("1200.00"),
                    LocalDateTime.now(),
                    LocalDateTime.now()
                    ));
        });

        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(productRepository).findById(1L);
    }

    @Test
    void deleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productServiceImpl.deleteProduct(1L);

        verify(productRepository).delete(1L);
        verify(productRepository).existsById(1L);
    }

    @Test
    void deleteProduct_ProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productServiceImpl.deleteProduct(1L);
        });

        assertEquals("Product not found with id: 1", exception.getMessage());
        verify(productRepository).existsById(1L);
    }
}