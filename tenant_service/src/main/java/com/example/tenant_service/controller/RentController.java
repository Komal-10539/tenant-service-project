package com.example.tenant_service.controller;

import com.example.tenant_service.dto.RentPaymentDto;
import com.example.tenant_service.dto.RentRecordDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.entity.RentPaymentProof;
import com.example.tenant_service.repository.RentPaymentProofRepository;
import com.example.tenant_service.repository.RentRepository;
import com.example.tenant_service.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenant-service/api/v1/tenant/rent")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;
    private final RentRepository rentRepository;
    private final RentPaymentProofRepository rentPaymentProofRepository;

    @GetMapping("/history/upcoming")
    public ResponseEntity<Response> getUpcomingRent(
            @RequestParam String accommodationId,
            @RequestParam int page) {

        Page<RentPaymentDto> data1 =
                rentService.getUpcomingRent(accommodationId, page);
        Map<String, Object> data = new HashMap<>();
        data.put("content", data1.getContent());
        data.put("number", data1.getNumber());
        data.put("numberOfElements", data1.getNumberOfElements());
        data.put("totalElements", data1.getTotalElements());
        data.put("totalPages", data1.getTotalPages());

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                data
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/overdue")
    public ResponseEntity<Response> getOverdue(
            @RequestParam String accommodationId,
            @RequestParam int page) {

        Page<RentPaymentDto> data1 =
                rentService.getOverdue(accommodationId, page);
        Map<String, Object> data = new HashMap<>();
        data.put("content", data1.getContent());
        data.put("number", data1.getNumber());
        data.put("numberOfElements", data1.getNumberOfElements());
        data.put("totalElements", data1.getTotalElements());
        data.put("totalPages", data1.getTotalPages());

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                data
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<Response> getRentHistory(
            @RequestParam String accommodationId,
            @RequestParam int page) {

        Page<RentPaymentDto> data1 =
                rentService.getRentHistory(accommodationId, page);
        Map<String, Object> data = new HashMap<>();
        data.put("content", data1.getContent());
        data.put("number", data1.getNumber());
        data.put("numberOfElements", data1.getNumberOfElements());
        data.put("totalElements", data1.getTotalElements());
        data.put("totalPages", data1.getTotalPages());

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                data
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/proof", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> uploadRentProof(
            @RequestParam String invoiceId,
            @RequestParam("proofs") List<MultipartFile> proofs) {

        rentService.uploadRentProof(invoiceId, proofs);

        return ResponseEntity.ok(
                new Response("O000", "Proof uploaded successfully", true, null)
        );
    }

    @GetMapping("/proof/{invoiceId}")
    public ResponseEntity<byte[]> getRentProof(@PathVariable String invoiceId) {

        RentPaymentProof proof = rentPaymentProofRepository
                .findTopByRent_InvoiceIdOrderByUploadedAtDesc(invoiceId)
                .orElseThrow(() -> new RuntimeException("Proof not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(proof.getFileType()))
                .body(proof.getData());
    }

    @GetMapping("/record")
    public ResponseEntity<Response> getRentRecord(
            @RequestParam String accommodationId
    ) {

        RentRecordDto dto =
                rentService.getRentRecord(accommodationId);

        return ResponseEntity.ok(
                new Response(
                        "INFO000",
                        "Success",
                        true,
                        dto
                )
        );
    }
}
