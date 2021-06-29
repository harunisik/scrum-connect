package com.harunisik.userdata.exception;

import lombok.Getter;

@Getter
public class UserdataException extends RuntimeException {

    private ExceptionType exceptionType;

    public UserdataException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

}
