package com.example.tenant_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentDetailsDto {

    private Double upcomingRent;
    private Double totalDues;
    private LocalDate dueDate;



}
