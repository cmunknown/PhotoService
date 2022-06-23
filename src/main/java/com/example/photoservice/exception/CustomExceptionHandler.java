package com.example.photoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(IdNotFountException.class)
    public ResponseEntity<String> handlerServiceException(IdNotFountException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotSupportedFormat.class)
    public ResponseEntity<String> handlerServiceException(NotSupportedFormat ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
