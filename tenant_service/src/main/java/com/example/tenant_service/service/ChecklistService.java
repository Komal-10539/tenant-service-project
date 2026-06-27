package com.example.tenant_service.service;

import com.example.tenant_service.dto.ChecklistResponse;
import com.example.tenant_service.entity.Checklist;
import com.example.tenant_service.enums.ChecklistType;
import com.example.tenant_service.repository.ChecklistRepository;
import org.springframework.stereotype.Service;

@Service
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    public ChecklistResponse getChecklist(String accommodationId, String type) {

        ChecklistType checklistType = ChecklistType.valueOf(type);

        Checklist checklist = checklistRepository
                .findByAccommodationIdAndType(accommodationId, checklistType)
                .orElse(null);

        if (checklist == null) {
            return new ChecklistResponse("PENDING");
        }

        return new ChecklistResponse(checklist.getStatus().name());
    }
}
