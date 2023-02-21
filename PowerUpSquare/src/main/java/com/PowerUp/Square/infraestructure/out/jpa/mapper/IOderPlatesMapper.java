package com.PowerUp.Square.infraestructure.out.jpa.mapper;

import com.PowerUp.Square.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOderPlatesMapper {
    /*@Mappings({
            @Mapping(target="orderEntity", source="orderEntity"),
            @Mapping(target="plateEntity", source="plateEntity"),
            @Mapping(target="amount", source="amount")
    }) */
}
