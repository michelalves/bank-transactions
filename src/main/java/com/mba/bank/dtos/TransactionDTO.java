package com.mba.bank.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
  private Long accountId;
  private Long operationTypeId;
  private BigDecimal amount;
}
