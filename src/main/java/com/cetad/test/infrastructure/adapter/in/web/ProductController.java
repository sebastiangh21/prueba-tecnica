package com.cetad.test.infrastructure.adapter.in.web;

import com.cetad.test.application.port.in.ManageProductUseCase;
import com.cetad.test.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ManageProductUseCase manageProductUseCase;

    public ProductController(ManageProductUseCase manageProductUseCase) {
        this.manageProductUseCase = manageProductUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(manageProductUseCase.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(manageProductUseCase.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        return new ResponseEntity<>(manageProductUseCase.getProductByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(manageProductUseCase.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(manageProductUseCase.updateProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        manageProductUseCase.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
