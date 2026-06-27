package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Checklist;
import com.example.tenant_service.enums.ChecklistType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    Optional<Checklist> findByAccommodationIdAndType(
            String accommodationId,
            ChecklistType type
    );
}
