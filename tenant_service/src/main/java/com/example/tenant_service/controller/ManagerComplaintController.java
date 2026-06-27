package com.example.tenant_service.controller;

import com.example.tenant_service.dto.RecentComplaintDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.StatusUpdateResponse;
import com.example.tenant_service.enums.ComplaintStatus;
import com.example.tenant_service.service.ManagerComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenant-service/api/v1/manager")
@RequiredArgsConstructor
public class ManagerComplaintController {
    private final ManagerComplaintService managerComplaintService;

    @GetMapping("/complaint/list")
    public ResponseEntity<Response> getComplaints(
            @RequestParam String accommodationId,
            @RequestParam(required = false) ComplaintStatus status,
            @RequestParam int page
    ) {

        Page<RecentComplaintDto> response1 =
                managerComplaintService.getAllComplaints(accommodationId, status, page);
        Map<String, Object> data = new HashMap<>();
        data.put("content", response1.getContent());
        data.put("number", response1.getNumber());
        data.put("numberOfElements", response1.getNumberOfElements());
        data.put("totalElements", response1.getTotalElements());
        data.put("totalPages", response1.getTotalPages());

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                data
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("document/{complaintNumber}")
    public ResponseEntity<Response> getAttachments(
            @PathVariable String complaintNumber) {

        List<String> attachments =
                managerComplaintService.getAttachments(complaintNumber);

        Response response = new Response(
                "INFO000",
                "Attachments fetched successfully",
                true,
                attachments
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/complaint/update")
    public ResponseEntity<Response> updateStatus(@RequestBody StatusUpdateResponse request) {

        managerComplaintService.updateStatus(
                request.getComplaintNumber(),
                request.getAccommodationId(),
                request.getStatus()
        );

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, null)
        );
    }

}
