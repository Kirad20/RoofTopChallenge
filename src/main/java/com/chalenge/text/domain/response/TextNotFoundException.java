package com.chalenge.text.domain.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TextNotFoundException extends RuntimeException{

  public TextNotFoundException() {
    super("Text not found");
  }
}
