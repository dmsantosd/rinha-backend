package com.rinha.backend.service;

import com.rinha.backend.dto.TransactionBalance;
import com.rinha.backend.entity.Client;

import java.math.BigInteger;

public interface TransactionProcessService {
    TransactionBalance process(final Client client, final BigInteger value);
}
