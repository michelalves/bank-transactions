package com.mba.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mba.bank.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
  boolean existsByDocument(String document);
}
