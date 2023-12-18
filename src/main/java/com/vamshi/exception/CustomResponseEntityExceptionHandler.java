package com.vamshi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ProductServiceCustomException.class)
    protected ResponseEntity<ApiError> handleInternalServerError(ProductServiceCustomException exception) {

        return new ResponseEntity<>(ApiError
                .builder()
                .message(exception.getMessage())
                .errorCode(exception.errorCode)
                .build(), HttpStatus.NOT_FOUND);

    }
}
