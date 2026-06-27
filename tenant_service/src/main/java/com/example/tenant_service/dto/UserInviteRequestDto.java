package com.example.tenant_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserInviteRequestDto {

    private String accommodationId;
    private String name;
    private String mobileNumber;
    private List<String> roles;
}
