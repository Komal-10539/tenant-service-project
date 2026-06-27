package com.example.tenant_service.service;

import com.example.tenant_service.dto.AdvanceDetailRequest;
import com.example.tenant_service.dto.DetailResponseDto;
import com.example.tenant_service.dto.TenantListDto;
import com.example.tenant_service.entity.Hostel;
import com.example.tenant_service.entity.Room;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.HostelRepository;
import com.example.tenant_service.repository.RoomRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DetailResponseDto getDetails(String accommodationId, Long tenantId) {
        User user = userRepository.findByHostel_AccommodationIdAndId(accommodationId, tenantId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return convertToDto(user);
    }

    private DetailResponseDto convertToDto(User user) {
        DetailResponseDto dto = new DetailResponseDto();

        dto.setAadhaarVerifiedName(user.getName());
        dto.setAccommodationDetail(user.getHostel().getAccommodationId());
        dto.setCareOf(user.getCareOf());
        dto.setDob(user.getDob());
        dto.setStatus(user.getStatus());
        dto.setMobileNumber(user.getUserName());
        dto.setEmailId(user.getEmailId());
        dto.setEmploymentNature(user.getEmploymentNature());
        dto.setGender(user.getGender());
        dto.setLocalRef(user.getLocalRef());
        dto.setOccupation(user.getOccupation());
        dto.setOfficeAddress(user.getOfficeAddress());
        dto.setOfficeNumber(user.getOfficeNumber());
        dto.setPermanentAddress(user.getPermanentAddress());
        dto.setPinCode(user.getPinCode());
        dto.setRentalRecord(user.getRentalRecord());
        dto.setTenantFullName(user.getName());
        dto.setTenantUserName(user.getUserName());
        dto.setUuid(user.getUuid());
        dto.setEditableFields(List.of(
                "localRef1",
                "occupation",
                "localRef2",
                "employmentNature",
                "emailId",
                "officeAddress"
        ));

        return dto;

    }

    public void saveAdvanceDetails(AdvanceDetailRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        User user = userRepository
                .findByHostel_AccommodationIdAndId(
                        request.getAccommodationId(),
                        request.getTenantId()
                );

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if ("Temp Name".equalsIgnoreCase(user.getName())) {

            if (request.getEmailId() != null) {
                user.setEmailId(request.getEmailId());
            }

            user.setOccupation(request.getOccupation());
            user.setOfficeNumber(request.getOfficeNumber());
            user.setOfficeAddress(request.getOfficeAddress());
            user.setEmailId(request.getEmailId());
            user.setWorkPlace(request.getWorkPlace());
            user.setEmploymentNature(request.getEmploymentNature());
            user.setLocalAddress(request.getLocalAddress());
            user.setPassword(passwordEncoder.encode("343434"));

            String ref1 = request.getLocalRef1() != null ? request.getLocalRef1() : "";
            String ref2 = request.getLocalRef2() != null ? request.getLocalRef2() : "";

            user.setLocalRef(ref1 + "," + ref2);

            userRepository.save(user);
        }
    }
}