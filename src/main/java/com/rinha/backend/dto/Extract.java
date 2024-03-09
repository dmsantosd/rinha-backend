package com.rinha.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Extract {
    @JsonProperty("saldo")
    private Balance balance;

    @JsonProperty("ultimas_transacoes")
    private List<Transaction> lastTransactions;
}
