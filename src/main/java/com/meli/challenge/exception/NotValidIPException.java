package com.meli.challenge.exception;

import javax.validation.ValidationException;

public class NotValidIPException extends ValidationException {

  public NotValidIPException(String message) {
    super(message);
  }

}