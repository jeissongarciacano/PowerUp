
package com.PowerUp.User.infraestructure.out.jpa.mapper;

import com.PowerUp.User.domain.model.Role;
import com.PowerUp.User.infraestructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name"),
            @Mapping(target="description", source="description")
    })
    RoleEntity toEntity(Role role);
    Role toRole(Optional<RoleEntity> roleEntity);
}