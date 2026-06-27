package com.example.tenant_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordResponseDto {
    private String dueDate;
    private String initialMeterReading;
    private boolean onNotice;
    private LocalDate joiningDate;
    private String noticeEndDate;
    private String noticeStartDate;
    private String noticeStatus;
    private String perDayLateCharge;
    private String rentAmount;
    private String roomId;
    private String roomNumber;
    private String securityDeposit;
    private String status;
    private Long tenantId;
}

