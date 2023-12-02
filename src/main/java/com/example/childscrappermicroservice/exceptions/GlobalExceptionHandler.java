package com.example.childscrappermicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({ParentScrapperNotFound.class})
    public ResponseEntity<Object> handleParentScrapperNotFoundException(ParentScrapperNotFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ParentScrapperInternalProblem.class)
    public ResponseEntity<Object> handleParentScrapperINternalProblem(ParentScrapperInternalProblem exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(ChildScrapperInternalProblem.class)
    public ResponseEntity<Object> handleChildScrapperInternalProblem(ChildScrapperInternalProblem exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
