package com.PowerUp.User.infraestructure.out.jpa.mapper;

import com.PowerUp.User.infraestructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleMapper {
    RoleEntity toEntity(Role role);
    Role toRole(RoleEntity roleEntity);
}
