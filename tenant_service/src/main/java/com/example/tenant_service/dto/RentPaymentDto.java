package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentPaymentDto {

    private String invoiceId;
    private String dueDate;
    private String paidAt;
    private Double rentAmount;
    private Double lateCharges;
    private Double electricityCharges;
    private String status;


}
