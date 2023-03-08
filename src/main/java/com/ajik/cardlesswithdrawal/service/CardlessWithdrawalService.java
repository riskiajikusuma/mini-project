package com.ajik.cardlesswithdrawal.service;

import com.ajik.cardlesswithdrawal.dto.*;

public interface CardlessWithdrawalService {

    GenerateReferenceCodeResponse generateReferenceCode(GenerateReferenceCodePayload dto);
    WithdrawalResponse withdrawal(WithdrawalPayload dto);
}
