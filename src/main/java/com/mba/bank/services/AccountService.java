package com.mba.bank.services;

import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.mba.bank.dtos.AccountDTO;
import com.mba.bank.entities.Account;
import com.mba.bank.entities.Transaction;
import com.mba.bank.enums.OperationType;
import com.mba.bank.enums.TransactionType;
import com.mba.bank.exceptions.AccountException;
import com.mba.bank.exceptions.AccountNotFoundException;
import com.mba.bank.exceptions.CreditLimitException;
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

    if (accountDTO.getCreditLimit() == null
        || accountDTO.getCreditLimit().compareTo(BigDecimal.ZERO) < 0)
      throw new CreditLimitException("Credit limit invalid");

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

  public void updateCreditLimit(Transaction transaction) {
    Account account = transaction.getAccount();
    if (isAnExpense(transaction.getOperationType())
        && hasInsufficientCreditLimit(account.getCreditLimit(), transaction.getAmount().abs()))
      throw new CreditLimitException("Credit limit insufficient");

    account.setCreditLimit(account.getCreditLimit().add(transaction.getAmount()));

    repository.save(account);
  }

  private boolean hasInsufficientCreditLimit(BigDecimal creditLimit, BigDecimal amount) {
    return creditLimit.compareTo(amount) < 0;
  }

  private boolean isAnExpense(OperationType operationType) {
    return TransactionType.EXPENSE.equals(operationType.getTransactionType());
  }
}
