package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomResponseDto<T> {
    private HttpStatus status;
    private String code;
    private String message;
    private T data;

    public CustomResponseDto(CustomException e) {
        if (e.getErrorCode() != null) {
            this.code = e.getErrorCode().getCode();
        }
        this.message = e.getMessage();
        this.status = e.getHttpStatus();
    }

    public CustomResponseDto(CustomException e, T data) {
        if (e.getErrorCode() != null) {
            this.code = e.getErrorCode().getCode();
        }
        this.message = e.getMessage();
        this.status = e.getHttpStatus();
        this.data = data;
    }

    public CustomResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomResponseDto(String message) {
        this.message = message;
    }
}