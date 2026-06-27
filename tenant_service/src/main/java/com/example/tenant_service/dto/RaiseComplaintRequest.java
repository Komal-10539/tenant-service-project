package com.example.tenant_service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RaiseComplaintRequest {

    private String subject;
    private String description;
    private String accommodationId;

    private MultipartFile images;

}
