package com.mba.bank.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mba.bank.dtos.TransactionDTO;
import com.mba.bank.entities.Account;
import com.mba.bank.entities.Transaction;
import com.mba.bank.enums.OperationType;
import com.mba.bank.mappers.TransactionMapper;
import com.mba.bank.repositories.TransactionsRepository;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceTest {

  private static final Account ACCOUNT_ID = Account.builder().id(1L).build();
  private static final Long OPERATION_TYPE_ID = 2L;
  private static final OperationType OPERATION_TYPE = OperationType.INSTALLMENT;
  private static final BigDecimal AMOUNT = BigDecimal.TEN;
  @Mock private TransactionsRepository repository;
  @Mock private TransactionMapper mapper;
  @InjectMocks private TransactionsService service;

  @Test
  public void registerTransaction() {
    Transaction transaction = buildTransaction(11L);
    TransactionDTO transactionDTO = buildTransactionDTO();
    when(repository.save(transaction)).thenReturn(transaction);
    when(mapper.toEntity(transactionDTO)).thenReturn(transaction);

    Long id = service.registerTransaction(transactionDTO);

    assertThat(id).isEqualTo(11L);
  }

  private Transaction buildTransaction(Long id) {
    return Transaction.builder()
        .id(id)
        .account(ACCOUNT_ID)
        .operationType(OPERATION_TYPE)
        .amount(AMOUNT)
        .build();
  }

  private TransactionDTO buildTransactionDTO() {
    return TransactionDTO.builder()
        .accountId(ACCOUNT_ID.getId())
        .operationTypeId(OPERATION_TYPE_ID)
        .amount(AMOUNT)
        .build();
  }
}
