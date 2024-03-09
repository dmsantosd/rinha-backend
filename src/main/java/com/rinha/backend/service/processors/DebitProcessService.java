package com.rinha.backend.service.processors;

import com.rinha.backend.config.DebitProcess;
import com.rinha.backend.dto.TransactionBalance;
import com.rinha.backend.entity.Client;
import com.rinha.backend.exception.ClientLimitException;
import com.rinha.backend.service.TransactionProcessService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@DebitProcess
public class DebitProcessService implements TransactionProcessService {

    @Override
    public TransactionBalance process(final Client client, final BigInteger value) {
        checkSufficientFunds(client, value);

        return TransactionBalance.builder()
                .balance(Optional.ofNullable(client.getBalance()).orElse(BigInteger.ZERO).subtract(value))
                .limit(Optional.ofNullable(client.getLimit()).orElse(BigInteger.ZERO))
                .build();
    }

    private void checkSufficientFunds(Client client, BigInteger value) {
        Optional<BigInteger> balance = Optional.ofNullable(client.getBalance());
        Optional<BigInteger> limit = Optional.ofNullable(client.getLimit());

        BigInteger totalFunds = balance.orElse(BigInteger.ZERO).add(limit.orElse(BigInteger.ZERO));

        if (totalFunds.subtract(value).compareTo(BigInteger.ZERO) < 0) {
            throw new ClientLimitException("Insufficient funds to perform the transaction.");
        }
    }
}
