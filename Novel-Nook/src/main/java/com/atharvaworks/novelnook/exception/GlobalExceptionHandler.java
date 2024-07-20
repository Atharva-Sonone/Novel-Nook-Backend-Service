package com.atharvaworks.novelnook.exception;

import org.springframework.data.mongodb.core.mapping.FieldName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> throwBadRequestException(BadRequestException e){
         ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage(), ZonedDateTime.now());
         return new ResponseEntity<Object> (exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> throwMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
                ValidationResponse validationResponse = new ValidationResponse(HttpStatus.BAD_REQUEST,errors,ZonedDateTime.now());
        return new ResponseEntity<Object>(validationResponse,HttpStatus.BAD_REQUEST);
    }
}
