package com.mba.bank.enums;

import java.math.BigDecimal;

public enum TransactionType {
  INCOME,
  EXPENSE;

  public BigDecimal getValueAmountByType(BigDecimal amount) {
    return this.equals(EXPENSE) ? amount.negate() : amount;
  }
}
