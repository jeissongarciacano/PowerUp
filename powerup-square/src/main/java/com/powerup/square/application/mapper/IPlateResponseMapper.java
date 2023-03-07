package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.PlateResponse;
import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateResponseMapper {
    PlateResponse toPlateResponse(Plate plate);

}
