package com.rinha.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class TransactionBalance {
    @JsonProperty("saldo")
    private BigInteger balance;

    @JsonProperty("limite")
    private BigInteger limit;
}
