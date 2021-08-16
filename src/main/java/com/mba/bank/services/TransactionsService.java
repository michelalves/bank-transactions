package com.mba.bank.services;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.mba.bank.dtos.TransactionDTO;
import com.mba.bank.mappers.TransactionMapper;
import com.mba.bank.repositories.TransactionsRepository;

@Service
@AllArgsConstructor
public class TransactionsService {

  private TransactionsRepository repository;
  private TransactionMapper mapper;

  public Long registerTransaction(TransactionDTO transactionDTO) {
    return repository.save(mapper.toEntity(transactionDTO)).getId();
  }
}
