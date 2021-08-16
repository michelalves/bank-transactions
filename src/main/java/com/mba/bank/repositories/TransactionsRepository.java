package com.mba.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mba.bank.entities.Transaction;

@Repository
public interface TransactionsRepository extends CrudRepository<Transaction, Long> {}
