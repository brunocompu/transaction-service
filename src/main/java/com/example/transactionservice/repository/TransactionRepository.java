package com.example.transactionservice.repository;

import com.example.transactionservice.model.Transaction;

public class TransactionRepository {
	private int count = 0;

	public boolean isEmpty() {
	    return count == 0;
	}

	public void save(Transaction transaction) {
	    count++;
	}

}
