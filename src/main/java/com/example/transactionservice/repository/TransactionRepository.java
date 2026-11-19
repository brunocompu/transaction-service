package com.example.transactionservice.repository;

import java.util.Optional;

import com.example.transactionservice.model.Transaction;

public class TransactionRepository {
	private Transaction transaction;

    public boolean isEmpty() {
        return transaction == null;
    }

    public void save(Transaction transaction) {
        this.transaction = transaction;
    }

    public Optional<Transaction> findById(long id) {
        return Optional.ofNullable(transaction);
    }

}
