package com.example.tenant_service.service;

import com.example.tenant_service.dto.*;
import com.example.tenant_service.entity.Hostel;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.HostelRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AadhaarService {
    private final UserRepository userRepository;
    private final HostelRepository hostelRepository;

    public AadhaarVerificationResponse verifyAadhaar(AadhaarVerificationRequest request) {

        String aadhaar = request.getAadhaar();
        String mobile = request.getMobile();
        if (aadhaar == null || aadhaar.isEmpty()) {
            throw new RuntimeException("Aadhaar number is required");
        }

        aadhaar = aadhaar.replaceAll("[^0-9]", "");

        if (aadhaar.length() != 12) {
            throw new RuntimeException("Invalid Aadhaar number");
        }
        if (mobile == null || mobile.isEmpty()) {
            throw new RuntimeException("Mobile number is required");
        }

        boolean isValid = true;

        return AadhaarVerificationResponse.builder()
                .isValid(isValid)
                .build();
    }

        public OtpVerificationResponse verifyOtp(OtpVerificationRequest request) {

            if (request == null) {
                throw new IllegalArgumentException("Request cannot be null");
            }

            if (request.getMobileNumber() == null || request.getMobileNumber().isEmpty()) {
                throw new IllegalArgumentException("Mobile number is required");
            }

            if (request.getOtp() == null || request.getOtp().isEmpty()) {
                throw new IllegalArgumentException("OTP is required");
            }


            Hostel hostel = hostelRepository
                    .findByAccommodationId(request.getAccommodationId())
                    .orElseThrow(() -> new RuntimeException("Accommodation not found"));

            User tenant = userRepository
                    .findByMobileNumber(request.getMobileNumber())
                    .orElseGet(() -> {
                        User newTenant = new User();
                        newTenant.setMobileNumber(request.getMobileNumber());
                        newTenant.setUserName(request.getMobileNumber()); // IMPORTANT (used in DTO)
                        newTenant.setName("Temp Name");

                        newTenant.setHostel(hostel);

                        return userRepository.save(newTenant);
                    });


            if (tenant.getHostel() == null) {
                tenant.setHostel(hostel);
                tenant = userRepository.save(tenant);
            }

            return OtpVerificationResponse.builder()
                    .tenantId(tenant.getId())
                    .fullName(tenant.getName())
                    .build();
     }
    }

