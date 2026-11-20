package com.example.transactionservice.service;

import com.example.transactionservice.repository.TransactionRepository;

import java.util.List;

import com.example.transactionservice.model.Transaction;

public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
    
    public void saveTransaction(Transaction transaction) {
        repository.save(transaction);
    }
    
    public List<Long> findTransactionIdsByType(String type) {
        return repository.findByType(type).stream()
                .map(Transaction::getId)
                .toList();
    }
    
    public double calculateSum(long transactionId) {

        Transaction transaction = repository.findById(transactionId).get();

        double sum = transaction.getAmount();

        List<Transaction> children = repository.findByParentId(transactionId);

        for (Transaction child : children) {
            sum += calculateSum(child.getId());
        }

        return sum;
    }
}