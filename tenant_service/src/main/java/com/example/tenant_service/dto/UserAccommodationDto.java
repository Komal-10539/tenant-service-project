package com.example.tenant_service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserAccommodationDto {
    private String accommodationId;
    private String accommodationName;
    private String role;

}
