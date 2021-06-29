package com.harunisik.userdata.exception;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserdataException.class)
    @Order(HIGHEST_PRECEDENCE)
    @ResponseBody
    public ResponseEntity<UserdataExceptionResponse> handleException(UserdataException exception) {
        log.error("Error: ", exception);
        ExceptionType exceptionType = exception.getExceptionType();
        UserdataExceptionResponse userdataExceptionResponse = convertToResponse(exceptionType);
        return new ResponseEntity<>(userdataExceptionResponse, exceptionType.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<UserdataExceptionResponse> handleAllExceptions(Exception exception) {
        log.error("Error: ", exception);
        UserdataExceptionResponse userdataExceptionResponse = convertToResponse(ExceptionType.INTERNAL_ERROR);
        return new ResponseEntity<>(userdataExceptionResponse, INTERNAL_SERVER_ERROR);
    }

    private UserdataExceptionResponse convertToResponse(ExceptionType exceptionType) {
        return UserdataExceptionResponse.builder()
            .errorCode(exceptionType.getCode())
            .description(exceptionType.getMessage())
            .timestamp(Timestamp.valueOf(LocalDateTime.now()))
            .build();
    }

}