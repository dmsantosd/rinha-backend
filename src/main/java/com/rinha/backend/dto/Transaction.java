package com.rinha.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    @JsonProperty(value = "valor", required = true)
    @Positive(message = "O valor deve ser maior ou igual à zero")
    @Digits(integer = 38, fraction = 0, message = "O valor deve ter no máximo {integer} dígitos e {fraction} casas decimais")
    private BigDecimal value;

    @JsonProperty(value = "tipo", required = true)
    @NotBlank(message = "O tipo é obrigatório")
    @Pattern(regexp = "[cd]", message = "O tipo deve ser 'c' ou 'd'")
    private String type;

    @JsonProperty(value = "descricao", required = true)
    @NotBlank(message = "A descrição é obrigatória")
    @Length(min = 1, max = 10, message = "A descrição deverá ter no máximo {max} caracteres")
    private String description;

    @JsonProperty("realizada_em")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

}
