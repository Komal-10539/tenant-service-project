package com.example.tenant_service.dto;

import com.example.tenant_service.enums.DocumentStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DocListDto {
    private String docName;
    private String docLink;
    private DocumentStatus Status;
}
