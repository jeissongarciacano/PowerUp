package com.PowerUp.Square.infraestructure.out.jpa.mapper;

import com.PowerUp.Square.domain.model.Category;
import com.PowerUp.Square.infraestructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeMapper {
    @Mappings({
            @Mapping(target="idRestaurant;", source="idRestaurant;"),
            @Mapping(target="idUser", source="idUser"),
            @Mapping(target="field", source="field")
    })
    CategoryEntity toEntity(Category category);
    Category toCategory(Optional<CategoryEntity> CategoryEntity);
}
