package com.example.tenant_service.dto;

import lombok.Data;

@Data
public class AdvanceDetailRequest {

    private String accommodationId;
    private String roomId;
    private Long tenantId;

    private String occupation;
    private String officeNumber;
    private String officeAddress;
    private String emailId;
    private String workPlace;
    private String employmentNature;

    private String localAddress;
    private String localRef1;
    private String localRef2;
}