package com.example.tenant_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AadhaarVerificationResponse {
    private boolean isValid;
}
