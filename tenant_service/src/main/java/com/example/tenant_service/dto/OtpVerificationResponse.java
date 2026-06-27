package com.example.tenant_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpVerificationResponse {

    private Long tenantId;
    private String fullName;
}
