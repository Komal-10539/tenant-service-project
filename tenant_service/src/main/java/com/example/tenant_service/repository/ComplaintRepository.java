package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Complaint;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.ComplaintStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Complaint findTopByUserOrderByCreatedAtDesc(User user);
    Page<Complaint> findByAccommodationId(String accommodationId, Pageable pageable);
    Page<Complaint> findAll(Pageable pageable);
    Optional<Complaint> findByComplaintNumber(String complaintNumber);
    long countByAccommodationIdAndStatus(String accommodationId, ComplaintStatus status);

    List<Complaint> findTop5ByAccommodationIdOrderByCreatedAtDesc(String accommodationId);
    Page<Complaint> findByAccommodationIdAndStatus(
            String accommodationId,
            ComplaintStatus status,
            Pageable pageable
    );

}
