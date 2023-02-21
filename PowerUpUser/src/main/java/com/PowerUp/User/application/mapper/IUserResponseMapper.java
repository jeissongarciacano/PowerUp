package com.PowerUp.User.application.mapper;

import com.PowerUp.User.application.dto.UserResponse;
import com.PowerUp.User.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponse toUserResponse(User user);

}
