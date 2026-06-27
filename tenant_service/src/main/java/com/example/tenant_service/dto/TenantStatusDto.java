package com.example.tenant_service.dto;

import lombok.Data;

@Data
public class TenantStatusDto {

    private boolean mobileVerified;
    private boolean aadhaarVerified;
    private boolean detailsSubmitted;
    private boolean documentsUploaded;
    private boolean detailsVerified;
}
