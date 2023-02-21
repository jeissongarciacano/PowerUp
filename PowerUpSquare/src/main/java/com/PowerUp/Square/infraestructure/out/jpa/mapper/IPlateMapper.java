package com.PowerUp.Square.infraestructure.out.jpa.mapper;

import com.PowerUp.Square.domain.model.Plate;
import com.PowerUp.Square.infraestructure.out.jpa.entity.PlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name"),
            @Mapping(target="category", source="category"),
            @Mapping(target="description", source="description"),
            @Mapping(target="price", source="price"),
            @Mapping(target="restaurant", source="Restaurant"),
            @Mapping(target="urlImage", source="urlImage")
    })
    PlateEntity toEntity(Plate plate);
    Plate toPlate(Optional<PlateEntity> PlateEntity);
}
