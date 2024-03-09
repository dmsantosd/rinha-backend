package com.rinha.backend.service;

import com.rinha.backend.dto.Balance;
import com.rinha.backend.dto.Extract;
import com.rinha.backend.dto.Transaction;
import com.rinha.backend.dto.TransactionBalance;
import com.rinha.backend.entity.Client;
import com.rinha.backend.enumeration.TransactionType;
import com.rinha.backend.exception.ClientNotFoundException;
import com.rinha.backend.repository.ClientRepository;
import com.rinha.backend.repository.TransactionRepository;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final Map<TransactionType, TransactionProcessService> processTransaction;
    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;

    public TransactionService(Map<TransactionType, TransactionProcessService> processTransaction,
                              TransactionRepository transactionRepository,
                              ClientRepository clientRepository
    ) {
        this.processTransaction = processTransaction;
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
    }

    public Client getClient(final Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(
                () -> new ClientNotFoundException("Client with id " + clientId + " not found")
        );
    }

    public Extract getExtract(final Long clientId) {
        var client = getClient(clientId);
        var transaction =
                transactionRepository.findByClientIdOrderByCreatedAtDesc(clientId, Limit.of(10)).orElse(List.of());

        return Extract.builder()
                .balance(Balance.builder()
                                 .extractDate(LocalDateTime.now())
                                 .limit(Optional.ofNullable(client.getLimit()).orElse(BigInteger.ZERO))
                                 .total(Optional.ofNullable(client.getBalance()).orElse(BigInteger.ZERO))
                                 .build())
                .lastTransactions(transaction.stream().map(t -> Transaction.builder()
                                .value(t.getValue())
                                .description(t.getDescription())
                                .type(t.getType())
                                .createdAt(t.getCreatedAt())
                                .build())
                                          .collect(Collectors.toList()))
                .build();

    }

    public TransactionBalance saveTransaction(final Long clientId, final Transaction transaction) {
        var client = getClient(clientId);

        var transactionBalance = processTransaction
                .get(TransactionType.valueOfCode(transaction.getType()))
                .process(client, transaction.getValue());

        client.setBalance(transactionBalance.getBalance());

        transactionRepository.save(com.rinha.backend.entity.Transaction.builder()
                                           .client(client)
                                           .type(transaction.getType())
                                           .value(transaction.getValue())
                                           .description(transaction.getDescription())
                                           .createdAt(LocalDateTime.now())
                                           .build());
        return transactionBalance;
    }
}
