package com.rinha.backend.controller;

import com.rinha.backend.dto.Extract;
import com.rinha.backend.dto.Transaction;
import com.rinha.backend.dto.TransactionBalance;
import com.rinha.backend.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{clientId}/extrato")
    public ResponseEntity<Extract> getTransaction(@PathVariable final Long clientId) {
        log.debug("get extract to client id: {}", clientId);
        return ResponseEntity.ok(transactionService.getExtract(clientId));
    }

    @PostMapping("/{clientId}/transacoes")
    public ResponseEntity<TransactionBalance> getTransaction(@PathVariable final Long clientId,
                                                             @Valid @RequestBody Transaction transaction
    ) {
        log.debug("save transaction to client id: {}", clientId);
        return ResponseEntity.ok(transactionService.saveTransaction(clientId, transaction));
    }

}