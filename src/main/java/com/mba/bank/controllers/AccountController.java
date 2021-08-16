package com.mba.bank.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mba.bank.dtos.AccountDTO;
import com.mba.bank.services.AccountService;

@RestController
@RequestMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {

  private AccountService accountService;

  @PostMapping
  public ResponseEntity<Long> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountDTO));
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<AccountDTO> getAccounts(@PathVariable("id") Long accountId) {
    return ResponseEntity.ok(accountService.getAccountById(accountId));
  }
}
