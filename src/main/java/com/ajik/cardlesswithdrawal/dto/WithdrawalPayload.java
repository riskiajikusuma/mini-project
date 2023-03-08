package com.ajik.cardlesswithdrawal.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WithdrawalPayload {

    @NotBlank
    private String referenceCode;

    @NotBlank
    private String merchantCode;
}
