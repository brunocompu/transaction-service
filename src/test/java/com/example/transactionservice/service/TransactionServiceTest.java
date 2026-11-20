package com.example.transactionservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	
	@Test
	void findTransactionIdsByTypeWithOneMatch() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction transaction1 = new Transaction(1L, 100.0, "food", null);
	    Transaction transaction2 = new Transaction(2L, 200.0, "clothes", null);

	    service.saveTransaction(transaction1);
	    service.saveTransaction(transaction2);

	    List<Long> result = service.findTransactionIdsByType("food");

	    assertEquals(1, result.size());
	    assertEquals(1L, result.get(0));
	}
	
	@Test
	void findTransactionIdsByTypeWithMultipleMatches() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction transaction1 = new Transaction(1L, 100.0, "food", null);
	    Transaction transaction2 = new Transaction(2L, 200.0, "clothes", null);
	    Transaction transaction3 = new Transaction(3L, 300.0, "food", null);

	    service.saveTransaction(transaction1);
	    service.saveTransaction(transaction2);
	    service.saveTransaction(transaction3);

	    List<Long> result = service.findTransactionIdsByType("food");

	    assertEquals(2, result.size());
	    assertTrue(result.contains(1L));
	    assertTrue(result.contains(3L));
	}
	
	@Test
	void calculateSumWithoutChildren() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction transaction = new Transaction(1L, 100.0, "food", null);
	    service.saveTransaction(transaction);

	    double result = service.calculateSum(1L);

	    assertEquals(100.0, result, 0.001);
	}
	
	@Test
	void calculateSumWithOneChild() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction parent = new Transaction(1L, 100.0, "food", null);
	    Transaction child = new Transaction(2L, 50.0, "clothes", 1L);

	    service.saveTransaction(parent);
	    service.saveTransaction(child);

	    double result = service.calculateSum(1L);

	    assertEquals(150.0, result, 0.001);
	}
	
	@Test
	void calculateSumWithMultipleChildren() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction parent = new Transaction(1L, 100.0, "food", null);
	    Transaction child1 = new Transaction(2L, 50.0, "clothes", 1L);
	    Transaction child2 = new Transaction(3L, 25.0, "food", 1L);
	    Transaction child3 = new Transaction(4L, 10.0, "books", 1L);

	    service.saveTransaction(parent);
	    service.saveTransaction(child1);
	    service.saveTransaction(child2);
	    service.saveTransaction(child3);

	    double result = service.calculateSum(1L);

	    assertEquals(185.0, result, 0.001);
	}
	
	@Test
	void calculateSumWithNestedChildren() {

	    TransactionRepository repository = new TransactionRepository();
	    TransactionService service = new TransactionService(repository);

	    Transaction transaction1 = new Transaction(1L, 100.0, "food", null);
	    Transaction transaction2 = new Transaction(2L, 50.0, "clothes", 1L);
	    Transaction transaction3 = new Transaction(3L, 25.0, "books", 2L);
	    Transaction transaction4 = new Transaction(4L, 10.0, "games", 3L);

	    service.saveTransaction(transaction1);
	    service.saveTransaction(transaction2);
	    service.saveTransaction(transaction3);
	    service.saveTransaction(transaction4);

	    double result = service.calculateSum(1L);

	    assertEquals(185.0, result, 0.001);
	}
}