package com.order.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ResourceExceptionHandler{




    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleConflict(BusinessException ex) {
        log.debug("Business", ex);
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }


}
