package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Order;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {
    OrderEntity toEntity(Order order);
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="idClient", source="idClient"),
            @Mapping(target="date", source="date"),
            @Mapping(target="state", source="state"),
            @Mapping(target="idChef", source="chef.id"),
            @Mapping(target="idRestaurant", source="restaurant.id")
    })
    Order toOrder(OrderEntity orderEntity);
}
