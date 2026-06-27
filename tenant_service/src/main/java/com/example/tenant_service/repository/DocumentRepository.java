package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByUser_Uuid(String uuid);
    Optional<Document> findByAccommodationIdAndDocName(String accommodationId, String docName);
    List<Document> findByAccommodationId(String accommodationId);
    List<Document> findByAccommodationIdIsNull();
}


