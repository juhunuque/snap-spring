package org.training.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.training.controllers.exceptions.DepartmentNotFoundException;
import org.training.controllers.exceptions.InvalidPayloadException;
import org.training.models.ErrorModel;

@ControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorModel> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        ErrorModel error = new ErrorModel();
        error.setReasonCode(101);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity<ErrorModel> handleDepartmentNotFoundException(InvalidPayloadException ex) {
        ErrorModel error = new ErrorModel();
        error.setReasonCode(102);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
