package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class UpdateStaffDto {

    private String userId;
    private List<String> roles;
}
