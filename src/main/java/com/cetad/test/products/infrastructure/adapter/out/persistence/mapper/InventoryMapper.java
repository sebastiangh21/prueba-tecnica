package com.cetad.test.products.infrastructure.adapter.out.persistence.mapper;

import com.cetad.test.products.domain.model.Inventory;
import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.InventoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryMapper {
    @Mappings({
            @Mapping(source = "idInventory", target = "idInventory"),
            @Mapping(source = "availableQuantity", target = "availableQuantity"),
            @Mapping(source = "productEntity", target = "product"),
    })
    Inventory toInventory(InventoryEntity entity);

    @InheritInverseConfiguration
    InventoryEntity toInventoryEntity(Inventory inventory);
}
