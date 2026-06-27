package com.example.tenant_service.controller;

import com.example.tenant_service.dto.RentApprovalResponse;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.entity.RentPaymentProof;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.repository.RentPaymentProofRepository;
import com.example.tenant_service.repository.RentRepository;
import com.example.tenant_service.service.RentApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/rent")
@RequiredArgsConstructor
public class RentApprovalController {
    private final RentApprovalService rentApprovalService;
    private final RentRepository rentRepository;
    private final RentPaymentProofRepository rentPaymentProofRepository;

    @GetMapping("/history")
    public ResponseEntity<Response> getApproval(
            @RequestParam String accommodationId,
            @RequestParam (required = false)RentStatus status,
            @RequestParam int page
    ){
        Page<?> response = rentApprovalService.getApproval(accommodationId, status, page);

        return ResponseEntity.ok(
                new Response("INFO000", "Success", true, response)
        );

    }


}
