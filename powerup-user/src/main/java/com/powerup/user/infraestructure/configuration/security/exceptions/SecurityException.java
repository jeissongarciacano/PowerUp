package com.powerup.user.infraestructure.configuration.security.exceptions;

import com.powerup.user.domain.exception.ErrorCodesEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class SecurityException extends RuntimeException {
    private final ErrorCodesEnum code;

    public SecurityException(ErrorCodesEnum code) {
        super(code.getMessage());
        this.code = code;
    }
}
