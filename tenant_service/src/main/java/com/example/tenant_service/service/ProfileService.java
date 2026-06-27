package com.example.tenant_service.service;

import com.example.tenant_service.dto.AccommodationDetailDto;
import com.example.tenant_service.dto.DocListDto;
import com.example.tenant_service.dto.LocalRefsDto;
import com.example.tenant_service.dto.ProfileResponse;
import com.example.tenant_service.entity.Document;
import com.example.tenant_service.entity.Hostel;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.DocumentRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;

    public ProfileResponse getprofile(String accoommodationId){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user=userRepository.findByUserName(username).get(0);

        ProfileResponse response=new ProfileResponse();
        response.setUuid(user.getUuid());
        response.setTenantFullName(user.getName());
        response.setTenantUserName(user.getUserName());

        response.setCareOf(user.getCareOf());
        response.setOccupation(user.getOccupation());
        response.setEmailId(user.getEmailId());
        response.setOfficeNumber(user.getOfficeNumber());

        response.setDob(user.getDob());
        response.setGender(user.getGender());
        response.setEmploymentNature(user.getEmploymentNature());

        response.setOfficeAddress(user.getOfficeAddress());
        response.setLocalAddress(user.getLocalAddress());
        response.setPermanentAddress(user.getPermanentAddress());
        response.setPinCode(user.getPinCode());

        Hostel hostel = user.getHostel();

        if (hostel != null) {
            AccommodationDetailDto accommodation = new AccommodationDetailDto();
            accommodation.setName(hostel.getName());
            accommodation.setLocality(hostel.getAddress());

            response.setAccommodationDetail(accommodation);
        }
       LocalRefsDto dto=new LocalRefsDto();
        dto.setName(user.getLocalRef());
        dto.setMobile(user.getLocalRefMobile());
        dto.setRelation(user.getLocalRefRelation());
        response.setLocalRef(List.of(dto));

        List<Document> documents = documentRepository.findByUser_Uuid(user.getUuid());

        List<DocListDto> docList = documents.stream().map(doc -> {

            DocListDto dto1 = new DocListDto();

            dto1.setDocName(doc.getDocName());
            dto1.setDocLink(doc.getDocLink());
            dto1.setStatus(doc.getStatus());

            return dto1;

        }).toList();

        response.setDocList(docList);

        return response;
        }

    public ProfileResponse getprofileupdate() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByUserName(username).get(0);

        ProfileResponse response = new ProfileResponse();
        response.setUuid(user.getUuid());
        response.setTenantFullName(user.getName());
        response.setTenantUserName(user.getUserName());

        response.setCareOf(user.getCareOf());
        response.setOccupation(user.getOccupation());
        response.setEmailId(user.getEmailId());
        response.setOfficeNumber(user.getOfficeNumber());

        response.setDob(user.getDob());
        response.setGender(user.getGender());
        response.setEmploymentNature(user.getEmploymentNature());

        response.setOfficeAddress(user.getOfficeAddress());
        response.setLocalAddress(user.getLocalAddress());
        response.setPermanentAddress(user.getPermanentAddress());
        response.setPinCode(user.getPinCode());
        response.setLocalRef1(user.getLocalRef1());
        response.setLocalRef2(user.getLocalRef2());

        return response;
    }
}
