package com.mba.bank.exceptions;

public class OperationTypeNotFound extends RuntimeException {

  private static final String message = "Operation Type not found";

  public OperationTypeNotFound() {
    super(message);
  }
}
