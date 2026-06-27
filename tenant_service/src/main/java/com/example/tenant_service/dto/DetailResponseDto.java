package com.example.tenant_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class DetailResponseDto {
    private String aadhaarVerifiedName;
    private String accommodationDetail;
    private String careOf;
    private String dob;
    private String emailId;
    private String employmentNature;
    private String gender;
    private String localRef;
    private String mobileNumber;
    private String occupation;
    private String officeAddress;
    private String officeNumber;
    private String permanentAddress;
    private String pinCode;
    private String rentalRecord;
    private String status;
    private String tenantFullName;
    private String tenantUserName;
    private String uuid;
    private List<String> editableFields;
}
