package com.PowerUp.Square.infraestructure.out.jpa.mapper;

import com.PowerUp.Square.domain.model.Order;
import com.PowerUp.Square.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="idClient", source="idClient"),
            @Mapping(target="date", source="date"),
            @Mapping(target="state", source="state"),
            @Mapping(target="idChef", source="idChef"),
            @Mapping(target="restaurant", source="idRestaurant")
    })
    OrderEntity toEntity(Order order);
    Order toOrder(Optional<OrderEntity> orderEntity);
}
