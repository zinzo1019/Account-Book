package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private CustomErrorCode errorCode;

    private HttpStatus httpStatus;
    private String message;
    private Exception exception;

    public CustomException(CustomErrorCode errorCode) {
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }

    public CustomException(CustomErrorCode errorCode, Exception exception) {
        this(errorCode);
        this.exception = exception;
    }

    public CustomException(HttpStatus httpStatus, String message, Exception exception) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.exception = exception;
    }
}

