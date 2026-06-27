package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.VerifiedPaymentResponse;
import com.example.tenant_service.enums.PaymentMode;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.service.VerifiedPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/rent/approval/request")
@RequiredArgsConstructor
public class VerifiedPaymentController {
    private final VerifiedPaymentService verifiedPaymentService;

    @PostMapping("/update")
    public ResponseEntity<Response> getVerified( @RequestBody VerifiedPaymentResponse request)
    {
        verifiedPaymentService.verifyPayment(
                request.getAccommodationId(),
                request.getInvoiceId(),
                request.getPaymentMode(),
                request.getStatus()
            );

            return ResponseEntity.ok(
                new Response("INFO000", "Payment approved successfully", true, null)
        );
    }

}
