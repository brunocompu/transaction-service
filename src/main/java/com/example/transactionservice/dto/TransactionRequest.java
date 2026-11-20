package com.example.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionRequest(
        double amount,
        String type,

        @JsonProperty("parent_id")
        Long parentId
) {}