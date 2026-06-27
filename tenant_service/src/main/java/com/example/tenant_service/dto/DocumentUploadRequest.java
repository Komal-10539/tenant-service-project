package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DocumentUploadRequest {

    private String accommodationId;

    private String docName;

}
