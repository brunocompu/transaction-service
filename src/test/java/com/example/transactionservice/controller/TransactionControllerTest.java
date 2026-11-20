package com.example.transactionservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.model.Transaction;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository repository;

    @Test
    void saveTransaction() throws Exception {

        mockMvc.perform(put("/transactions/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "amount": 100.0,
                            "type": "food",
                            "parent_id": 5
                        }
                        """))
                .andExpect(status().isOk());

        Transaction transaction = repository.findById(10L).orElseThrow();

        assertEquals(100.0, transaction.getAmount());
        assertEquals("food", transaction.getType());
        assertEquals(5L, transaction.getParentId());
    }
    
    @Test
    void saveTransactionWithoutParent() throws Exception {

        mockMvc.perform(put("/transactions/20")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "amount": 75.0,
                            "type": "books"
                        }
                        """))
                .andExpect(status().isOk());

        Transaction transaction = repository.findById(20L).orElseThrow();

        assertEquals(75.0, transaction.getAmount());
        assertEquals("books", transaction.getType());
        assertNull(transaction.getParentId());
    }
    
    @Test
    void findTransactionIdsByTypeWithoutMatches() throws Exception {

        mockMvc.perform(get("/transactions/types/food"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    
    
}