package com.PowerUp.User.application.mapper;

import com.PowerUp.User.application.dto.UserRequest;
import com.PowerUp.User.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name"),
            @Mapping(target="lastName", source="lastName"),
            @Mapping(target="idDocument", source="idDocument"),
            @Mapping(target="phone", source="phone"),
            @Mapping(target="email", source="email"),
            @Mapping(target="password", source="password")
    })
    User toUser(UserRequest userRequest);
}
