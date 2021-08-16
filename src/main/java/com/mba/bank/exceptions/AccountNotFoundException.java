package com.mba.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountNotFoundException extends AccountException {
  private static final String message = "Not Found account";

  public AccountNotFoundException() {
    super(message);
  }
}
