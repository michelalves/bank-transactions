package com.mba.bank.controllers;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mba.bank.dtos.TransactionDTO;
import com.mba.bank.services.TransactionsService;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionsController {

  private TransactionsService transactionsService;

  @PostMapping
  public ResponseEntity<Long> registerTransaction(
      @Valid @RequestBody TransactionDTO transactionDTO) {
    return ResponseEntity.ok(transactionsService.registerTransaction(transactionDTO));
  }
}
