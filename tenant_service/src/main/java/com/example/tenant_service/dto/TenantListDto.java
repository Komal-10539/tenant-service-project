package com.example.tenant_service.dto;

import lombok.Data;

@Data
public class TenantListDto {
    private String tenantId;
    private String name;
    private String profilePic;
    private String roomNumber;
    //private String accommodationId;
    private String mobileNumber;
    private String status;
    private boolean rentPaid;
    private boolean onNotice;
}
