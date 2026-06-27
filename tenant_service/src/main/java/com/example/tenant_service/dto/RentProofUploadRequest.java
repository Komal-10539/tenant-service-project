package com.example.tenant_service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class RentProofUploadRequest {

    private String invoiceId;
    private List<MultipartFile> proofs;

}
