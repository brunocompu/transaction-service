package com.example.transactionservice.service;

import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.model.Transaction;

public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
    
    public void saveTransaction(Transaction transaction) {
        repository.save(transaction);
    }
}