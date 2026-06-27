package com.example.tenant_service.service;

import com.example.tenant_service.dto.DocumentUploadResponse;
import com.example.tenant_service.dto.OnboardingDocumentDto;
import com.example.tenant_service.entity.Document;
import com.example.tenant_service.enums.DocumentStatus;
import com.example.tenant_service.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

   /* public List<OnboardingDocumentDto> getOnboardingDocuments(String accommodationId) {

        List<Document> docs = documentRepository.findAll();
        //List<Document> docs = documentRepository.findByAccommodationId(accommodationId);

        return docs.stream().map(doc -> {
            OnboardingDocumentDto dto = new OnboardingDocumentDto();
            dto.setDocName(doc.getDocName());
            dto.setRequired(doc.getRequired());
            dto.setDocId(doc.getId());
            dto.setDocLink("/tenant-service/api/v1/tenant/document/view/" + doc.getId());
            //dto.setStatus(String.valueOf(DocumentStatus.UPLOADED));
            dto.setUploadDate(doc.getUploadDate());
            return dto;
        }).toList();
    }*/
   public List<OnboardingDocumentDto> getOnboardingDocuments(String accommodationId) {

       List<Document> requiredDocs = documentRepository.findByAccommodationIdIsNull();

       List<Document> uploadedDocs =
               documentRepository.findByAccommodationId(accommodationId);

       Map<String, Document> uploadedMap =
               uploadedDocs.stream()
                       .collect(Collectors.toMap(Document::getDocName, d -> d));

       List<OnboardingDocumentDto> response = new ArrayList<>();

       for (Document master : requiredDocs) {

           OnboardingDocumentDto dto = new OnboardingDocumentDto();

           dto.setDocName(master.getDocName());
           dto.setRequired(master.getRequired());

           Document uploaded = uploadedMap.get(master.getDocName());

           if (uploaded != null) {
               dto.setDocId(uploaded.getId());
               dto.setStatus("UPLOADED");
               dto.setUploadDate(uploaded.getUploadDate());
               dto.setDocLink("/tenant-service/api/v1/tenant/document/view/" + uploaded.getId());
           } else {
               dto.setStatus("PENDING");
           }

           response.add(dto);
       }

       return response;
   }

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
