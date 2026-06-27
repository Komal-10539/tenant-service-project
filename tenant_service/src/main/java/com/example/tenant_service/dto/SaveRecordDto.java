package com.example.tenant_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveRecordDto {
    private LocalDate joiningDate;
    private LocalDate dueDate;
    private Long tenantId;
    private String roomNumber;
    private String roomId;
    private String status;
    private double rentAmount;
    private String initialMeterReading;
}
