package com.PowerUp.Square.application.mapper;

import com.PowerUp.Square.application.dto.RestaurantRequest;
import com.PowerUp.Square.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name"),
            @Mapping(target="address", source="address"),
            @Mapping(target="phone", source="phone"),
            @Mapping(target="urlLogo", source="urlLogo"),
            @Mapping(target="nit", source="nit")
    })
    Restaurant toRestaurant(RestaurantRequest restaurantRequest);
}
