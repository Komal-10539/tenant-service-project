package com.example.tenant_service.service;

import com.example.tenant_service.dto.TenantListDto;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {
    private final UserRepository userRepository;

    public Page<TenantListDto> getTenants(String accommodationId , int page){
        System.out.println("AccommodationId received: " + accommodationId);


        Pageable pageable =
                PageRequest.of(page,10);

        Page<User> user =
                userRepository.findByHostel_AccommodationIdAndRole(
                accommodationId,
                "TENANT",
                pageable
        );
        System.out.println("Total tenants found: " + user.getTotalElements());

        return user.map(this::convertToDto);
    }

    private TenantListDto convertToDto(User user) {
           TenantListDto dto = new TenantListDto();

            dto.setTenantId(String.valueOf(user.getId()));
            dto.setName(user.getName());
            dto.setRoomNumber(user.getRoom().getRoomNumber());
            dto.setStatus(user.getStatus());
            dto.setMobileNumber(user.getUserName());
            dto.setProfilePic(null);
            dto.setRentPaid(false);
            dto.setOnNotice(false);

            return dto;

    }

}

