package com.example.tenant_service.dto;

import com.example.tenant_service.enums.ComplaintStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
public class StatusUpdateResponse {
    private String complaintNumber;
    private String accommodationId;
    private ComplaintStatus status;
}
