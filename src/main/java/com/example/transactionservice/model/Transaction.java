package com.example.transactionservice.model;

public class Transaction {

    public Transaction(long id, double amount, String type, Long parentId) {
    	
    }

    public long getId() {
        return 1L;
    }

    public double getAmount() {
        return 100.0;
    }

    public String getType() {
        return "cars";
    }

    public Long getParentId() {
        return null;
    }
}
