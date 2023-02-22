package com.PowerUp.Square.application.mapper;

import com.PowerUp.Square.application.dto.RestaurantResponse;
import com.PowerUp.Square.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

}
