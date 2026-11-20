package com.example.transactionservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void createTransactionWithBasicAttributes() {
        long id = 1L;
        double amount = 100.0;
        String type = "cars";

        Transaction transaction = new Transaction(id, amount, type, null);

        assertEquals(id, transaction.getId());
        assertEquals(amount, transaction.getAmount());
        assertEquals(type, transaction.getType());
        assertNull(transaction.getParentId());
    }
    
    @Test
    void createTwoTransactionsWithBasicAttributes() {
        long id_1 = 1L;
        long id_2 = 2L;
        double amount_1 = 100.0;
        double amount_2 = 200.0;
        String type_1 = "cars";
        String type_2 = "shopping";
        Long parentId_1 = null;
        Long parentId_2 = 2L;

        Transaction firstTransaction = new Transaction(id_1, amount_1, type_1, parentId_1);
        Transaction secondTransaction = new Transaction(id_2, amount_2, type_2, parentId_2);

        assertAll(
                () -> assertNotSame(firstTransaction, secondTransaction),
                () -> assertEquals(id_1, firstTransaction.getId()),
                () -> assertEquals(amount_1, firstTransaction.getAmount()),
                () -> assertEquals(type_1, firstTransaction.getType()),
                () -> assertEquals(parentId_1, firstTransaction.getParentId()),
                () -> assertEquals(id_2, secondTransaction.getId()),
                () -> assertEquals(amount_2, secondTransaction.getAmount()),
                () -> assertEquals(type_2, secondTransaction.getType()),
                () -> assertEquals(parentId_2, secondTransaction.getParentId())
        );
    }
}