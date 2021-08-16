package com.mba.bank.enums;

import static com.mba.bank.enums.TransactionType.EXPENSE;
import static com.mba.bank.enums.TransactionType.INCOME;

import java.math.BigDecimal;
import java.util.Arrays;

import lombok.Getter;

import com.mba.bank.exceptions.OperationTypeNotFound;

public enum OperationType {
  CASH(EXPENSE),
  INSTALLMENT(EXPENSE),
  WITHDRAWAL(EXPENSE),
  PAYMENT(INCOME);

  @Getter private final TransactionType transactionType;

  OperationType(TransactionType type) {
    transactionType = type;
  }

  public Long getValue() {
    return Long.valueOf(ordinal() + 1);
  }

  public static OperationType fromCode(Long code) {
    return Arrays.stream(values())
        .filter(x -> x.getValue().equals(code))
        .findFirst()
        .orElseThrow(OperationTypeNotFound::new);
  }

  public BigDecimal getTypedAmount(BigDecimal amount) {
    return this.transactionType.getValueAmountByType(amount);
  }
}
