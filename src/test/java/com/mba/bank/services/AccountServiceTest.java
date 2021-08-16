package com.mba.bank.services;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mba.bank.dtos.AccountDTO;
import com.mba.bank.entities.Account;
import com.mba.bank.exceptions.AccountException;
import com.mba.bank.exceptions.AccountNotFoundException;
import com.mba.bank.mappers.AccountMapper;
import com.mba.bank.repositories.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  private static final String DOCUMENT_NUMBER = "12345678901";
  private static final long ACCOUNT_ID = 1L;
  @Mock private AccountRepository repository;
  @Mock private AccountMapper mapper;
  @InjectMocks private AccountService service;

  @Test
  public void whenDocumentIsValidThenCreateAccount() {
    AccountDTO accountDTO = buildAccountDTO(null);
    Account accountToSave = buildAccount(null);
    when(repository.save(accountToSave)).thenReturn(buildAccount(ACCOUNT_ID));
    when(mapper.toEntity(accountDTO)).thenReturn(accountToSave);

    Long accountId = service.createAccount(accountDTO);

    assertThat(accountId).isEqualTo(ACCOUNT_ID);
  }

  @Test
  public void whenFindAccountThenReturnAccountDto() {
    Account account = buildAccount(ACCOUNT_ID);
    when(repository.findById(ACCOUNT_ID)).thenReturn(of(account));
    AccountDTO accountDTO = buildAccountDTO(1L);
    when(mapper.toDto(account)).thenReturn(accountDTO);

    AccountDTO accountFound = service.getAccountById(ACCOUNT_ID);

    assertThat(accountFound).isEqualTo(accountDTO);
  }

  @Test
  public void whenNotFoundAccountThenThrowAccountNotFoundException() {

    AccountNotFoundException exception =
        assertThrows(
            AccountNotFoundException.class,
            () -> service.getAccountById(ACCOUNT_ID),
            "Expected getAccountById to throw, but it didn't");

    assertTrue(exception.getMessage().contains("Not Found account"));
  }

  @Test
  public void whenDocumentIsInvalidThrowError() {
    AccountDTO accountDTO = AccountDTO.builder().document("a1adada").build();
    AccountException exception =
        assertThrows(
            AccountException.class,
            () -> service.createAccount(accountDTO),
            "Expected getAccountById to throw, but it didn't");

    assertTrue(exception.getMessage().contains("Document invalid"));
  }

  @Test
  public void whenAlreadyExistAccountThenThrowError() {
    when(repository.existsByDocument(DOCUMENT_NUMBER)).thenReturn(true);

    AccountException exception =
        assertThrows(
            AccountException.class,
            () -> service.createAccount(buildAccountDTO(null)),
            "Expected getAccountById to throw, but it didn't");

    assertTrue(exception.getMessage().contains("Already exist an account with this document"));
  }

  private Account buildAccount(Long id) {
    return Account.builder().id(id).document(DOCUMENT_NUMBER).build();
  }

  private AccountDTO buildAccountDTO(Long id) {
    return AccountDTO.builder().document(DOCUMENT_NUMBER).build();
  }
}
