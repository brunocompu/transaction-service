package com.example.transactionservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void test01_createTransactionWithBasicAttributes() {
        long id = 1L;
        double amount = 100.0;
        String type = "cars";

        Transaction transaction = new Transaction(id, amount, type, null);

        assertEquals(id, transaction.getId());
        assertEquals(amount, transaction.getAmount());
        assertEquals(type, transaction.getType());
        assertNull(transaction.getParentId());
    }
}