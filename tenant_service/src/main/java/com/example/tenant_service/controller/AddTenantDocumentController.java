package com.example.tenant_service.controller;

import com.example.tenant_service.dto.DocumentUploadResponse;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.service.AddTenantDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/tenant-service/api/v1/manager/document")
@RequiredArgsConstructor
public class AddTenantDocumentController {
    private final AddTenantDocumentService addTenantDocumentService;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadDocument(
            @RequestParam String accommodationId,
            @RequestParam String docName,
            @RequestParam MultipartFile docFile) {

        DocumentUploadResponse response =
                addTenantDocumentService.uploadDocument(accommodationId, docName, docFile);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, null)
        );
    }
}
