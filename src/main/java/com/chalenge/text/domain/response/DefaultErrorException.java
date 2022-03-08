package com.chalenge.text.domain.response;

public class DefaultErrorException extends RuntimeException{

  public DefaultErrorException() {
    super("An error occurred when processing the text");
  }
}