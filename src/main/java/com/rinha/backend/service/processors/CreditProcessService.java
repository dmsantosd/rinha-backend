package com.rinha.backend.service.processors;

import com.rinha.backend.config.CreditProcess;
import com.rinha.backend.dto.TransactionBalance;
import com.rinha.backend.entity.Client;
import com.rinha.backend.service.TransactionProcessService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@CreditProcess
public class CreditProcessService implements TransactionProcessService {
    @Override
    public TransactionBalance process(final Client client, final BigInteger value) {
        return TransactionBalance.builder()
                .balance(Optional.ofNullable(client.getBalance()).orElse(BigInteger.ZERO).add(value))
                .limit(Optional.ofNullable(client.getLimit()).orElse(BigInteger.ZERO))
                .build();
    }
}
