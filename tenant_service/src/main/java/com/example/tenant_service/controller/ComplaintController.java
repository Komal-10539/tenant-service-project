package com.example.tenant_service.controller;

import com.example.tenant_service.dto.RaiseComplaintRequest;
import com.example.tenant_service.dto.RecentComplaintDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.entity.ComplaintDocument;
import com.example.tenant_service.repository.ComplaintDocumentRepository;
import com.example.tenant_service.repository.ComplaintRepository;
import com.example.tenant_service.service.ComplaintService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenant-service/api/tenant/")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ComplaintRepository complaintRepository;
    private final ComplaintDocumentRepository complaintDocumentRepository;

    public ComplaintController(ComplaintService complaintService,ComplaintRepository complaintRepository,ComplaintDocumentRepository complaintDocumentRepository) {
        this.complaintService = complaintService;
        this.complaintRepository = complaintRepository;
        this.complaintDocumentRepository =complaintDocumentRepository;
    }

    @GetMapping("/complaint")
    public ResponseEntity<Response> getComplaints(
            @RequestParam String accommodationId,
            @RequestParam(defaultValue = "0") int page
    ){
        Page<RecentComplaintDto> complaintPage =
                complaintService.getComplaints(accommodationId,page);

        Map<String,Object> data = new HashMap<>();
        data.put("content", complaintPage.getContent());
        data.put("number", complaintPage.getNumber());
        data.put("numberOfElements", complaintPage.getNumberOfElements());
        data.put("totalElements", complaintPage.getTotalElements());
        data.put("totalPages", complaintPage.getTotalPages());

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                data
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/complaint/raise", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Response> raiseComplaint(
            @ModelAttribute RaiseComplaintRequest request
    ) throws IOException {

        RecentComplaintDto complaint =
                complaintService.raiseComplaint(request);

        Response response = new Response(
                "INFO000",
                "Complaint raised successfully",
                true,
                complaint
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("document/{complaintNumber}")
    public ResponseEntity<Response> getAttachments(
            @PathVariable String complaintNumber) {

        List<String> attachments =
                complaintService.getAttachments(complaintNumber);

        Response response = new Response(
                "INFO000",
                "Attachments fetched successfully",
                true,
                attachments
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/complaint/document/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {

        ComplaintDocument doc = complaintDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + doc.getFileName() + "\"")
                .body(doc.getFileData());
    }

}