package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.order.OrderPlateResponse;
import com.powerup.square.application.dto.plate.PlateResponse;
import com.powerup.square.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateResponseMapper {
    PlateResponse toPlateResponse(Plate plate);
    OrderPlateResponse toOrderPlateResponse(Plate plate);

}
