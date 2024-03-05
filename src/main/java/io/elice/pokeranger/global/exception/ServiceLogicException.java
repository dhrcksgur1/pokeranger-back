package io.elice.pokeranger.global.exception;

import lombok.Getter;

public class ServiceLogicException {
    @Getter
    private final ExceptionCode exceptionCode;

    public ServiceLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
