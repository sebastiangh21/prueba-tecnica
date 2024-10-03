package com.cetad.test.products.infrastructure.adapter.in.web;

import com.cetad.test.products.application.port.in.ManageProductUseCase;
import com.cetad.test.products.domain.exception.ProductNotFoundException;
import com.cetad.test.products.domain.model.Product;
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

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ManageProductUseCase manageProductUseCase;

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Add a new product to the system.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Product created successfully",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Creating a new product: {}", product);
        try {
            Product createdProduct = manageProductUseCase.createProduct(product);
            logger.info("Product created successfully: {}", createdProduct);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating product", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a product by ID",
            description = "Retrieve a single product using its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product found",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        logger.debug("Fetching product with ID: {}", id);
        try {
            Product product = manageProductUseCase.getProductById(id);
            logger.info("Product found: {}", product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException p) {
            logger.warn("Product with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    @Operation(
            summary = "Get products by name",
            description = "Retrieve all products that match the provided name.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Products found",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
        logger.debug("Fetching products with name: {}", name);
        try {
            List<Product> products = manageProductUseCase.getProductByName(name);
            logger.info("Products found for name '{}': {}", name, products);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while fetching products with name", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Retrieve a list of all products.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Fetching all products");
        try {
            List<Product> products = manageProductUseCase.getAllProducts();
            logger.info("Successfully retrieved {} products", products.size());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while fetching all products", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a product",
            description = "Update the details of an existing product using its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product updated successfully",
                            content = @Content(schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        logger.info("Updating product with ID: {}", id);
        try {
            Product updatedProduct = manageProductUseCase.updateProduct(id, product);
            logger.info("Product updated successfully: {}", updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException p) {
            logger.error("Product not found with ID: {}", id, p);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while updating product with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a product",
            description = "Remove a product from the system using its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product with ID: {}", id);
        try {
            manageProductUseCase.deleteProduct(id);
            logger.info("Product with ID {} deleted successfully", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundException p) {
            logger.error("Product not found with ID: {}", id, p);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
