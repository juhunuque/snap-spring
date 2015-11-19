package org.training.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.training.controllers.exceptions.DepartmentNotFoundException;
import org.training.controllers.exceptions.InvalidPayloadException;

@ControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(InvalidPayloadException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
