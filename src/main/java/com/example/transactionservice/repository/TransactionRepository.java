package com.example.transactionservice.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.transactionservice.model.Transaction;

public class TransactionRepository {

    private final Map<Long, Transaction> transactions = new HashMap<>();

    public boolean isEmpty() {
        return transactions.isEmpty();
    }

    public void save(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
    }

    public Optional<Transaction> findById(long id) {
        return Optional.ofNullable(transactions.get(id));
    }
    
    public List<Transaction> findByType(String type) {
		return Collections.emptyList();
	}
}