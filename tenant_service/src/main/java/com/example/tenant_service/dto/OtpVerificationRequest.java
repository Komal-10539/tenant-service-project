package com.example.tenant_service.dto;

import lombok.Data;

@Data
public class OtpVerificationRequest {

    private String accommodationId;
    private String otp;
    private Object requestId;
    private String mobileNumber;

}
