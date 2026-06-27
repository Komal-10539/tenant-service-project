package com.example.tenant_service.service;

import com.example.tenant_service.dto.DocumentUploadResponse;
import com.example.tenant_service.entity.Document;
import com.example.tenant_service.enums.DocumentStatus;
import com.example.tenant_service.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class AddTenantDocumentService {

    private final DocumentRepository documentRepository;

    public DocumentUploadResponse uploadDocument(
            String accommodationId,
            String docName,
            MultipartFile docFile) {

        try {
            Document document = documentRepository
                    .findByAccommodationIdAndDocName(accommodationId, docName)
                    .orElse(new Document());

            document.setAccommodationId(accommodationId);
            document.setDocName(docName);
            document.setDocFile(docFile.getBytes());
            document.setContentType(docFile.getContentType());
            document.setStatus(DocumentStatus.UPLOADED);
            document.setDocLink("/tenant-service/api/v1/tenant/document/view/" + document.getId());
            document.setRequired(true);
            document.setUploadDate(LocalDateTime.now());

            Document saved = documentRepository.save(document);

            DocumentUploadResponse response = new DocumentUploadResponse();
            response.setDocId(saved.getId());
            response.setStatus(String.valueOf(saved.getStatus()));
            response.setUploadDate(saved.getUploadDate());
            //response.setDocLink(link);

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed",e);
        }
    }
}
