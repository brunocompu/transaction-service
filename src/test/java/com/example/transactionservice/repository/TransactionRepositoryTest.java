package com.example.transactionservice.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}


