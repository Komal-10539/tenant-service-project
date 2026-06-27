package com.example.tenant_service.dto;

import lombok.Data;

@Data
public class AadhaarVerificationRequest {
    private String aadhaar;
    private String mobile;
}