package com.example.tenant_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecentComplaintDto {

    private String complainType;
    private String complaintNumber;
    private String description;
    private boolean hasAttachment;
    private String roomId;
    private String status;
    private String accommodationId;
    private LocalDateTime createdAt;

}
