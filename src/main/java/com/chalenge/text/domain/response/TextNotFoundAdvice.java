package com.chalenge.text.domain.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TextNotFoundAdvice {
  
  @ResponseBody
  @ExceptionHandler(TextNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Map<String,Object> textNotFoundHandler(TextNotFoundException ex) {
    Map<String,Object> response = new HashMap<>();
    response.put("error",true);
    response.put("message", ex.getMessage());
    response.put("code",404);

    return response;
  }
}
