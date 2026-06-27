package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class StaffResponseDto {

    private String userId;
    private String name;
    private String mobileNumber;
    private List<String> roles;
    private String userStatus; // ACTIVE / PENDING / INACTIVE
    private String image;
}
