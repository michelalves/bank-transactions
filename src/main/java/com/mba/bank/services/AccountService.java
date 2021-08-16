package com.mba.bank.services;

import static java.util.Optional.ofNullable;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.mba.bank.dtos.AccountDTO;
import com.mba.bank.exceptions.AccountException;
import com.mba.bank.exceptions.AccountNotFoundException;
import com.mba.bank.mappers.AccountMapper;
import com.mba.bank.repositories.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {

  private AccountRepository repository;
  private AccountMapper mapper;

  public Long createAccount(AccountDTO accountDTO) {
    validateAccountCreation(accountDTO);
    return repository.save(mapper.toEntity(accountDTO)).getId();
  }

  private void validateAccountCreation(AccountDTO accountDTO) {
    validateDocument(accountDTO.getDocument());

    if (repository.existsByDocument(accountDTO.getDocument()))
      throw new AccountException("Already exist an account with this document");
  }

  private void validateDocument(String document) {
    Predicate<String> documentFilter = Pattern.compile("^\\d{11,11}$").asPredicate();

    ofNullable(document)
        .filter(documentFilter)
        .orElseThrow(() -> new AccountException("Document invalid"));
  }

  public AccountDTO getAccountById(Long accountId) {
    return repository
        .findById(accountId)
        .map(mapper::toDto)
        .orElseThrow(AccountNotFoundException::new);
  }
}
