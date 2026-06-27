package com.example.tenant_service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class TenantDetailResponse {
    private boolean aadhaarVerified;
    private boolean advanceKycVerified;
    private boolean docVerified;
    private String mobileNumber;
    private String status;
    private String tenantId;
    private String tenantName;

}
