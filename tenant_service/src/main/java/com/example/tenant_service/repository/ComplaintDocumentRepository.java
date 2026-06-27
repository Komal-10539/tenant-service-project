package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Complaint;
import com.example.tenant_service.entity.ComplaintDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintDocumentRepository extends JpaRepository<ComplaintDocument, Long> {
    List<ComplaintDocument> findByComplaintId(Long complaintId);
}
