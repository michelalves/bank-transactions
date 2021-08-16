package com.mba.bank.mappers;

import org.springframework.stereotype.Component;

import com.mba.bank.dtos.AccountDTO;
import com.mba.bank.entities.Account;

@Component
public class AccountMapper {

  public Account toEntity(AccountDTO accountDTO) {
    return Account.builder().document(accountDTO.getDocument()).build();
  }

  public AccountDTO toDto(Account account) {
    return AccountDTO.builder().id(account.getId()).document(account.getDocument()).build();
  }
}
