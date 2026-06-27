package com.example.tenant_service.controller;

import com.example.tenant_service.dto.DocumentUploadResponse;
import com.example.tenant_service.dto.OnboardingDocumentDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.entity.Document;
import com.example.tenant_service.repository.DocumentRepository;
import com.example.tenant_service.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("tenant-service/api/v1/tenant/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    @GetMapping("/onboarding")
    public ResponseEntity<Response> getOnboardingDocuments(@RequestParam String accommodationId) {

        List<OnboardingDocumentDto> data =
                documentService.getOnboardingDocuments(accommodationId);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, data)
        );
    }

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadDocument(
            @RequestParam String accommodationId,
            @RequestParam String docName,
            @RequestParam MultipartFile docFile) {

        DocumentUploadResponse response =
                documentService.uploadDocument(accommodationId, docName, docFile);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, null)
        );
    }

    @GetMapping("/view/{docId}")
    public ResponseEntity<byte[]> viewDocument(@PathVariable Long docId) {

        Document document = documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        String contentType = document.getContentType();

        if (contentType == null || contentType.isBlank()) {
            contentType = "image/jpeg";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getContentType()))
                .body(document.getDocFile());
    }
}
