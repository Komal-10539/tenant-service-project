package com.example.tenant_service.controller;

import com.example.tenant_service.dto.ChecklistResponse;
import com.example.tenant_service.service.ChecklistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/tenant")
public class ChecklistController {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @GetMapping("/checklist")
    public ChecklistResponse getChecklist(
            @RequestParam String accommodationId,
            @RequestParam String type
    ) {
        return checklistService.getChecklist(accommodationId, type);
    }
}
