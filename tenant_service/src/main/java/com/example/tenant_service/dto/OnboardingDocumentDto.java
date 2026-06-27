package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class OnboardingDocumentDto {

    private String docName;
    private Boolean required;
    private Long docId;
    private String docLink;
    private String status;
    private LocalDateTime uploadDate;

}
