package com.example.transactionservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
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
	
	@Test
	void saveTransactionWithExistingId() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction first = new Transaction(1L, 100.0, "food", null);
	    Transaction second = new Transaction(1L, 200.0, "clothes", null);

	    service.saveTransaction(first);
	    service.saveTransaction(second);

	    assertSame(second, repository.findById(1L).get());
	}
	
}