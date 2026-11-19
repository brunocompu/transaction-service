package com.example.transactionservice.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.transactionservice.model.Transaction;


public class TransactionRepositoryTest {
	@Test
    void test03_createEmptyRepository() {
        TransactionRepository repository = new TransactionRepository();

        assertTrue(repository.isEmpty());
    }
	
	@Test
    void test04_createRepositoryAndSaveTransaction() {
        TransactionRepository repository = new TransactionRepository();

        Transaction transaction =
                new Transaction(1L, 100.0, "cars", null);

        repository.save(transaction);

        assertFalse(repository.isEmpty());
    }
	
	@Test
    void test05_saveAndFindTransactionById() {
        TransactionRepository repository = new TransactionRepository();

        Transaction transaction =
                new Transaction(1L, 100.0, "cars", null);

        repository.save(transaction);

        assertTrue(repository.findById(1L).isPresent());
        assertSame(transaction, repository.findById(1L).get());
    }
	
	@Test
	void test06_saveAndFindTwoTransactions() {
	    TransactionRepository repository = new TransactionRepository();
	
	    Transaction transaction1 = new Transaction(1L, 100.0, "food", null);
	    Transaction transaction2 = new Transaction(2L, 50.0, "clothes", null);
	
	    repository.save(transaction1);
	    repository.save(transaction2);
	
	    assertSame(transaction1, repository.findById(1L).get());
	    assertSame(transaction2, repository.findById(2L).get());
	}
	
	@Test
	void test07_findNonexistentTransaction() {

	    TransactionRepository repository = new TransactionRepository();

	    Optional<Transaction> result = repository.findById(999L);

	    assertTrue(result.isEmpty());
	}
	
	@Test
	void test08_saveTransactionWithExistingId() {

	    TransactionRepository repository = new TransactionRepository();

	    Transaction transaction1 = new Transaction(1L, 100.0, "food", null);
	    Transaction transaction2 = new Transaction(1L, 200.0, "clothes", null);

	    repository.save(transaction1);
	    repository.save(transaction2);

	    assertSame(transaction2, repository.findById(1L).get());
	}
	
	@Test
	void test09_findTransactionsByTypeEmptyRepository() {

	    TransactionRepository repository = new TransactionRepository();

	    List<Transaction> result = repository.findByType("food");

	    assertTrue(result.isEmpty());
	}
	
	@Test
	void test10_findTransactionsByTypeWithNoMatches() {

	    TransactionRepository repository = new TransactionRepository();

	    Transaction transaction = new Transaction(1L, 200.0, "clothes", null);
	    
	    repository.save(transaction);
	    
	    List<Transaction> result = repository.findByType("food");

	    assertTrue(result.isEmpty());
	}
	
	@Test
	void test11_findTransactionsByTypeWithOneMatch() {

	    TransactionRepository repository = new TransactionRepository();

	    Transaction transaction1 = new Transaction(1L, 100.0, "food", null);
	    Transaction transaction2 = new Transaction(1L, 200.0, "clothes", null);

	    repository.save(transaction1);
	    repository.save(transaction2);
	    
	    List<Transaction> result = repository.findByType("food");

	    assertSame(result.get(0), transaction1);
	}
	
}


