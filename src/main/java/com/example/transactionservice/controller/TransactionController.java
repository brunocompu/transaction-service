package com.example.transactionservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.transactionservice.dto.TransactionRequest;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, String>> saveTransaction(
            @PathVariable long transactionId,
            @RequestBody TransactionRequest request) {

        Transaction transaction = new Transaction(
                transactionId,
                request.amount(),
                request.type(),
                request.parentId()
        );

        service.saveTransaction(transaction);

        return ResponseEntity.ok(Map.of("status", "ok"));
    }
    
    @GetMapping("/types/{type}")
    public ResponseEntity<List<Long>> findTransactionIdsByType(
            @PathVariable String type) {

        List<Long> transactionIds = service.findTransactionIdsByType(type);

        return ResponseEntity.ok(transactionIds);
    }
    
    @GetMapping("/sum/{transactionId}")
    public ResponseEntity<Map<String, Double>> calculateSum(
            @PathVariable long transactionId) {

        double sum = service.calculateSum(transactionId);

        return ResponseEntity.ok(Map.of("sum", sum));
    }
}