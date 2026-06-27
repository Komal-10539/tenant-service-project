package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ProfileResponse {
    private String uuid;
    private String tenantFullName;
    private String tenantUserName;
    private String careOf;
    private String occupation;
    private String emailId;
    private String officeNumber;
    private String dob;
    private String gender;
    private String employmentNature;
    private String officeAddress;
    private String localAddress;
    private String permanentAddress;
    private String pinCode;
    private String localRef1;
    private String localRef2;
    private List<LocalRefsDto> localRef;
    private List<DocListDto> docList;
    private AccommodationDetailDto accommodationDetail;
}
