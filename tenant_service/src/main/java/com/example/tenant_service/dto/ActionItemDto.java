package com.example.tenant_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionItemDto {
    private String type;
    private String title;
    private String subtitle;
    private String time;
}
