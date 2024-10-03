package com.cetad.test.products.infrastructure.adapter.out.persistence;

import com.cetad.test.products.application.port.out.ProductRepository;
import com.cetad.test.products.domain.exception.ProductNotFoundException;
import com.cetad.test.products.domain.model.Product;
import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.cetad.test.products.infrastructure.adapter.out.persistence.mapper.ProductMapper;
import com.cetad.test.products.infrastructure.adapter.out.persistence.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductPersistenceAdapter implements ProductRepository {

    @Autowired
    private JpaProductRepository jpaProductRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toProductEntity(product);
        if (entity.getIdProduct() != null) {
            ProductEntity existingEntity = jpaProductRepository.findById(entity.getIdProduct())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + entity.getIdProduct()));
            entity.setCreationDate(existingEntity.getCreationDate());
        }

        ProductEntity savedEntity = jpaProductRepository.save(entity);
        return mapper.toProduct(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id).map(mapper::toProduct);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = (List<ProductEntity>) jpaProductRepository.findAll();
        return mapper.toProducts(productEntities);
    }

    @Override
    public void delete(Long id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<ProductEntity> productEntities = jpaProductRepository.findByNameOrderByName(name);
        return mapper.toProducts(productEntities);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaProductRepository.existsById(id);
    }
}
