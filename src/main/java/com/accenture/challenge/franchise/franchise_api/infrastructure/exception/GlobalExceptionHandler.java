package com.accenture.challenge.franchise.franchise_api.infrastructure.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Le decimos a Spring: "Atrapa cualquier IllegalArgumentException que ocurra en la API"
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        
        // Construimos un JSON de error personalizado
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", ex.getMessage()); // Aquí irá nuestro texto: "La franquicia con ID ... no existe"

        // Retornamos un HTTP 400 en lugar del feo HTTP 500
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}