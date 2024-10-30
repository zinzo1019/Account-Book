package com.example.demo.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class ExceptionHandlerAspect {

    /**
     * CustomException
     * @param ex
     */
    @AfterThrowing(pointcut = "execution(* com.example.demo..*.*(..))", throwing = "ex")
    public void handleException(CustomException ex) {
        String exceptionMessage = Optional.ofNullable(ex.getException())
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());

        System.err.println("[ExceptionHandlerAspect] " + ex.getMessage() + (exceptionMessage != null ? ": " + exceptionMessage : ""));
    }

    /**
     * Exception
     * @param ex
     */
    @AfterThrowing(pointcut = "execution(* com.example.demo..*.*(..))", throwing = "ex")
    public void handleGeneralException(Exception ex) {
        String stackTraceInfo = "[ExceptionHandlerAspect] " + ex.getStackTrace()[0];
        String errorMessage = Optional.ofNullable(ex.getCause())
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());

        System.err.println(stackTraceInfo + (errorMessage != null ? ": " + errorMessage : ""));
    }
}