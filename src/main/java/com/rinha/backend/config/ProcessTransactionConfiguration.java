package com.rinha.backend.config;

import com.rinha.backend.enumeration.TransactionType;
import com.rinha.backend.service.TransactionProcessService;
import com.rinha.backend.service.processors.CreditProcessService;
import com.rinha.backend.service.processors.DebitProcessService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ProcessTransactionConfiguration {

    private final CreditProcessService processCredit;
    private final DebitProcessService processDebit;

    public ProcessTransactionConfiguration(CreditProcessService processCredit, DebitProcessService processDebit) {
        this.processCredit = processCredit;
        this.processDebit = processDebit;
    }

    @Bean
    public Map<TransactionType, TransactionProcessService> processTransaction() {
        return Map.of(TransactionType.CREDIT, processCredit,
                      TransactionType.DEBIT, processDebit);
    }
}