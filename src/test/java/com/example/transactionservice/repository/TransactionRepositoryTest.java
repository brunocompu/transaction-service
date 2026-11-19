package com.example.transactionservice.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TransactionRepositoryTest {
	@Test
    void test03_createEmptyRepository() {
        TransactionRepository repository = new TransactionRepository();

        assertTrue(repository.isEmpty());
    }
}


