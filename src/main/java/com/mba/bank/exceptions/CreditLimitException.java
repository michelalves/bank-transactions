package com.mba.bank.exceptions;

public class CreditLimitException extends AccountException {
  public CreditLimitException(String message) {
    super(message);
  }
}
