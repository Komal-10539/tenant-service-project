package com.example.tenant_service.dto;

import com.example.tenant_service.enums.RentStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class RentApprovalResponse {

    private String invoiceId;
    private String tenantName;
    private String roomNumber;
    private double rentAmount;
    private double lateCharges;
    private double electricityCharges;
    private LocalDate dueDate;
    private LocalDate paidAt;
    private String status;
    private String docLink;

}
