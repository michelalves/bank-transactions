package com.mba.bank.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.mba.bank.enums.OperationType;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Enumerated(EnumType.ORDINAL)
  private OperationType operationType;

  private BigDecimal amount;
}
