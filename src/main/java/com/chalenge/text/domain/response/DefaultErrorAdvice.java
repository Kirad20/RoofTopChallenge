package com.chalenge.text.domain.response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultErrorAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
      return new ResponseEntity<>(getBody(HttpStatus.UNPROCESSABLE_ENTITY, ex, "An error occurred when processing the text"),new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TextNotFoundException.class)
    public ResponseEntity<Object> handleTextNotFoundException(TextNotFoundException ex) {
        return new ResponseEntity<>(getBody(HttpStatus.NOT_FOUND, ex, ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    public Map<String, Object> getBody(HttpStatus status, Exception ex, String message) {

      Map<String, Object> body = new LinkedHashMap<>();
      body.put("message", message);
      body.put("status", status.value());
      body.put("error", true);

      return body;
  }

}

