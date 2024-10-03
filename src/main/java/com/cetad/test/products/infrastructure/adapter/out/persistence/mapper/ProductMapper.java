package com.cetad.test.products.infrastructure.adapter.out.persistence.mapper;

import com.cetad.test.products.domain.model.Product;
import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProduct", target = "idProduct"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "creationDate", target = "creationDate"),
            @Mapping(source = "modificationDate", target = "modificationDate"),
    })
    Product toProduct(ProductEntity entity);
    List<Product> toProducts(List<ProductEntity> entities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "inventoryEntity", ignore = true),
            @Mapping(target = "stockMovementEntities", ignore = true),
    })
    ProductEntity toProductEntity(Product product);
}
