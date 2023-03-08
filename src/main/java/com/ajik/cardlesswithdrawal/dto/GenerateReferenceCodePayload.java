package com.ajik.cardlesswithdrawal.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GenerateReferenceCodePayload {

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String merchantCode;

    @NotNull
    private Double requestAmount;
}
