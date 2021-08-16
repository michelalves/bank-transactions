package com.mba.bank.infra.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mba.bank.exceptions.AccountException;

@ControllerAdvice
@RestController
public class AccountExceptionHandler {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(AccountException.class)
  public String accountNotFound(AccountException e) {
    return e.getMessage();
  }

}
