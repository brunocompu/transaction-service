package com.example.transactionservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
	
	@Test
	void findTransactionIdsByTypeWithNoMatches() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    List<Long> result = service.findTransactionIdsByType("food");

	    assertTrue(result.isEmpty());
	}
	
}