package com.mba.bank.services;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.mba.bank.dtos.TransactionDTO;
import com.mba.bank.entities.Transaction;
import com.mba.bank.mappers.TransactionMapper;
import com.mba.bank.repositories.TransactionsRepository;

@Service
@AllArgsConstructor
public class TransactionsService {

  private TransactionsRepository repository;
  private TransactionMapper mapper;
  private AccountService accountService;

  public Long registerTransaction(TransactionDTO transactionDTO) {
    Transaction transaction = mapper.toEntity( transactionDTO );

    accountService.updateCreditLimit(transaction);

    return repository.save( transaction ).getId();
  }
}
