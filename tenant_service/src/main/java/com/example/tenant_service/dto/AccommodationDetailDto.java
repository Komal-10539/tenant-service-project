package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccommodationDetailDto {
    private String name;
    private String locality;
}
