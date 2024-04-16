package com.example.projetoteste.repositories;

import com.example.projetoteste.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {



}
