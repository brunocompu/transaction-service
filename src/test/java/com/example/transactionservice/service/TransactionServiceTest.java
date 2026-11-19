package com.example.transactionservice.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.repository.TransactionRepository;

class TransactionServiceTest {

	@Test
	void saveTransaction() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction transaction = new Transaction(1L, 100.0, "food", null);

	    service.saveTransaction(transaction);

	    assertTrue(repository.findById(1L).isPresent());
	}
}