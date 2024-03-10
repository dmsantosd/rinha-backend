package com.rinha.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
public class Balance {
    @JsonProperty("total")
    private BigInteger total;

    @JsonProperty("data_extrato")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime extractDate;

    @JsonProperty("limite")
    private BigInteger limit;
}
