package com.example.tenant_service.dto;

import com.example.tenant_service.enums.PaymentMode;
import com.example.tenant_service.enums.RentStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VerifiedPaymentResponse {

    private String invoiceId;
    private PaymentMode paymentMode;
    private String accommodationId;
    private RentStatus status;
}
