package com.example.tenant_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class OccupancyStatusDto {

    private String accommodationName;
    private int totalBeds;
    private int occupiedBeds;
    private int newlyFilled;
    private int vacated;
    private int onNotice;
}
