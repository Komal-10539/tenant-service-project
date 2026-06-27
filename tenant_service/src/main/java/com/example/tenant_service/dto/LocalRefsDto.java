package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LocalRefsDto {
    private String name;
    private String mobile;
    private String relation;
}
