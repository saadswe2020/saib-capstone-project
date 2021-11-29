package com.saib.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.models.Account;
import com.saib.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	
	List<Transaction> findByTransactionType(String transactionType);  
	List<Transaction> findByDate(LocalDateTime date); 
	List<Transaction> findTransactionByDateAndTransactionType(LocalDateTime date, String transactionType); 
	
}