package com.mba.bank.mappers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mba.bank.dtos.TransactionDTO;
import com.mba.bank.entities.Account;
import com.mba.bank.entities.Transaction;
import com.mba.bank.enums.OperationType;
import com.mba.bank.exceptions.AccountNotFoundException;
import com.mba.bank.repositories.AccountRepository;

@ExtendWith(MockitoExtension.class)
class TransactionMapperTest {

  private static final Long ACCOUNT_ID = 1L;
  private static final Long OPERATION_TYPE_INSTALLMENT_ID = 2L;
  private static final Long OPERATION_TYPE_PAYMENT_ID = 4L;
  private static final OperationType OPERATION_TYPE_INSTALLMENT = OperationType.INSTALLMENT;
  private static final BigDecimal AMOUNT = BigDecimal.TEN;
  private static final String DOCUMENT_NUMBER = "00010500540";
  private static final OperationType OPERATION_TYPE_PAYMENT = OperationType.PAYMENT;
  @Mock private AccountRepository accountRepository;
  @InjectMocks private TransactionMapper mapper;

  @Test
  public void toEntityWithNegateOperationType() {
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(buildAccount()));

    Transaction transaction = mapper.toEntity(buildTransactionDto(OPERATION_TYPE_INSTALLMENT_ID));

    Assertions.assertThat(transaction).isEqualTo(buildTransaction(OPERATION_TYPE_INSTALLMENT));
  }

  @Test
  public void toEntityWithPositiveOperationType() {
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(buildAccount()));

    Transaction transaction = mapper.toEntity(buildTransactionDto(OPERATION_TYPE_PAYMENT_ID));

    Assertions.assertThat(transaction).isEqualTo(buildTransaction(OPERATION_TYPE_PAYMENT));
  }

  @Test
  public void whenNotFoundAccount() {
    AccountNotFoundException exception =
        assertThrows(
            AccountNotFoundException.class,
            () -> mapper.toEntity(buildTransactionDto(OPERATION_TYPE_PAYMENT_ID)),
            "Expected toEntity to throw, but it didn't");

    assertTrue(exception.getMessage().contains("Not Found account"));
  }

  private Transaction buildTransaction(OperationType operationType) {
    return Transaction.builder()
        .account(buildAccount())
        .operationType(operationType)
        .amount(operationType != OperationType.PAYMENT ? AMOUNT.negate() : AMOUNT)
        .build();
  }

  private Account buildAccount() {
    return Account.builder().id(ACCOUNT_ID).document(DOCUMENT_NUMBER).build();
  }

  private TransactionDTO buildTransactionDto(Long operationTypeId) {
    return TransactionDTO.builder()
        .accountId(ACCOUNT_ID)
        .operationTypeId(operationTypeId)
        .amount(AMOUNT)
        .build();
  }
}
