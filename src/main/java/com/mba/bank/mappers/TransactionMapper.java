package com.mba.bank.mappers;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import com.mba.bank.dtos.TransactionDTO;
import com.mba.bank.entities.Transaction;
import com.mba.bank.enums.OperationType;
import com.mba.bank.exceptions.AccountNotFoundException;
import com.mba.bank.repositories.AccountRepository;

@Component
@AllArgsConstructor
public class TransactionMapper {

  private AccountRepository accountRepository;

  public Transaction toEntity(TransactionDTO transactionDTO) {
    OperationType operationType = OperationType.fromCode(transactionDTO.getOperationTypeId());
    return Transaction.builder()
        .account(
            accountRepository
                .findById(transactionDTO.getAccountId())
                .orElseThrow(AccountNotFoundException::new))
        .operationType(operationType)
        .amount(operationType.getTypedAmount(transactionDTO.getAmount()))
        .build();
  }
}
