package com.example.tenant_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentActivitiesDto {
    private String text;
    private String type;
    private String time;

}
