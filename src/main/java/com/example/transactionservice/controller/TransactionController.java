package com.example.transactionservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.transactionservice.dto.TransactionRequest;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.service.TransactionService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> saveTransaction(
            @PathVariable long transactionId,
            @RequestBody TransactionRequest request) {

        Transaction transaction = new Transaction(
                transactionId,
                request.amount(),
                request.type(),
                request.parentId()
        );

        service.saveTransaction(transaction);

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/types/{type}")
    public ResponseEntity<List<Long>> findTransactionIdsByType(
            @PathVariable String type) {

        List<Long> transactionIds = Collections.emptyList();

        return ResponseEntity.ok(transactionIds);
    }
}