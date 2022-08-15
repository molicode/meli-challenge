package com.meli.challenge.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Map<String, String>> exception(ValidationException ex) {
    return returnBadRequest(ex.getCause().getMessage());
  }

  @ExceptionHandler(NotValidIPException.class)
  public ResponseEntity<Map<String, String>> exception(NotValidIPException ex) {
    return returnBadRequest(ex.getMessage());
  }

  // If not found specific exception
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> exception(Exception exception) {
    Map<String, String> response = prepareResponse(
        exception.getMessage(),
        "Please try again later or contact with IT",
        HttpStatus.INTERNAL_SERVER_ERROR.toString());
    log.info("There is an unknown issue.", exception);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Map<String, String> prepareResponse(String error, String solution, String status) {
    // You can define any other class for better visualization for response
    Map<String, String> response = new HashMap<>();
    response.put("Cause", error);
    response.put("Solution", solution);
    response.put("Status", status);
    return response;
  }

  private ResponseEntity<Map<String, String>> returnBadRequest(String message) {
    Map<String, String> response = prepareResponse(
        message,
        "Please enter a valid entity with proper constraints",
        HttpStatus.BAD_REQUEST.toString());
    log.info("Entity is not valid.", message);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
