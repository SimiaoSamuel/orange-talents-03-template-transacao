package com.orangetalents.transacao.shared.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErroApiException.class)
    public ResponseEntity<Object> handleErroApiException(ErroApiException ex){
        return new ResponseEntity<>(new ErrorDto(ex),ex.getErrorStatus());
    }
}
