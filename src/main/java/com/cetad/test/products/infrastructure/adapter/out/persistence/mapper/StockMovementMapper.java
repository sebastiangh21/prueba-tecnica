package com.cetad.test.products.infrastructure.adapter.out.persistence.mapper;

import com.cetad.test.products.domain.model.StockMovement;
import com.cetad.test.products.infrastructure.adapter.out.persistence.entity.StockMovementEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
    @Mappings({
            @Mapping(source = "idMovement", target = "idMovement"),
            @Mapping(source = "idProduct", target = "idProduct"),
            @Mapping(source = "movementType", target = "movementType"),
            @Mapping(source = "movementDate", target = "movementDate"),
            @Mapping(source = "quantity", target = "quantity"),
    })
    StockMovement toStockMovement(StockMovementEntity entity);
    List<StockMovement> toStockMovements(List<StockMovementEntity> entities);

    @InheritInverseConfiguration
    StockMovementEntity toStockMovementEntity(StockMovement stockMovement);
}
