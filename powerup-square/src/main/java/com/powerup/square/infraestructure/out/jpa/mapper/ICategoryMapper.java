package com.PowerUp.Square.infraestructure.out.jpa.mapper;

import com.PowerUp.Square.domain.model.Category;
import com.PowerUp.Square.infraestructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name"),
            @Mapping(target="description", source="description")
    })
    CategoryEntity toEntity(Category category);
    Category toCategory(Optional<CategoryEntity> CategoryEntity);
}
