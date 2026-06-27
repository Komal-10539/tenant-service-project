package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class RentHistoryResponse {
    private long totalPayments;

    private List<RentDetailsDto> rents;

}
