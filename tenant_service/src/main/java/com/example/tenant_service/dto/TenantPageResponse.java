package com.example.tenant_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class TenantPageResponse {
    private List<TenantListDto> data;
    private int page;
    private boolean hasNext;
}
