package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class RentRecordDto {

    private String roomNumber;
    private LocalDate joiningDate;
    private Double rentAmount;
}