package com.example.tenant_service.controller;

import com.example.tenant_service.dto.*;
import com.example.tenant_service.service.AadhaarService;
import com.example.tenant_service.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/tenant/onboarding")
@RequiredArgsConstructor
public class AadhaarController {

    private final AadhaarService aadhaarService;
    private final DetailService detailService;

    @PostMapping("/aadhaar/verification")
    public ResponseEntity<Response> verifyAadhaar(
            @RequestBody AadhaarVerificationRequest request) {

        AadhaarVerificationResponse response =
                aadhaarService.verifyAadhaar(request);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, response)
        );
    }
    @PostMapping("/aadhaar/verification/otp")
    public ResponseEntity<Response> verifyOtp(
            @RequestBody OtpVerificationRequest request) {

        OtpVerificationResponse data = aadhaarService.verifyOtp(request);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, data)
        );
    }

    @PostMapping("/submit-advance-detail")
    public ResponseEntity<Response> submitAdvanceDetails(
            @RequestBody AdvanceDetailRequest request) {

        detailService.saveAdvanceDetails(request);

        return ResponseEntity.ok(
                new Response("INFO000", "Details saved successfully", true, null)
        );
    }
}
