package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TenantInviteRequest {
    private String mobile;
    private String accommodationId;
}
