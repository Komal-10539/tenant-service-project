package com.example.tenant_service.service;

import com.example.tenant_service.dto.StaffResponseDto;
import com.example.tenant_service.dto.UpdateStaffDto;
import com.example.tenant_service.dto.UserInviteRequestDto;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.entity.UserInvite;
import com.example.tenant_service.enums.Role;
import com.example.tenant_service.repository.UserInviteRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final UserRepository userRepository;
    private final UserInviteRepository userInviteRepository;

    public List<StaffResponseDto> getStaff(String accommodationId) {

        List<User> users = userRepository.findByHostel_AccommodationId(accommodationId);
        List<UserInvite> invites =
                userInviteRepository.findByAccommodationIdAndIsAcceptedFalse(accommodationId);

        List<StaffResponseDto> response = new ArrayList<>();

        // Users → ACTIVE / INACTIVE
        for (User user : users) {
            StaffResponseDto dto = new StaffResponseDto();
            dto.setUserId(String.valueOf(user.getId()));
            dto.setName(user.getName());
            dto.setMobileNumber(user.getMobileNumber());
            dto.setRoles(user.getRoles());
            dto.setUserStatus(Boolean.TRUE.equals(user.getIsActive()) ? "ACTIVE" : "INACTIVE"
            );
            dto.setImage(user.getImage());
            response.add(dto);
        }

        // Invites → PENDING
        for (UserInvite invite : invites) {
            StaffResponseDto dto = new StaffResponseDto();
            dto.setUserId(null); // frontend fallback
            dto.setName(invite.getName());
            dto.setMobileNumber(invite.getMobile());
            dto.setRoles(invite.getRoles());
            dto.setUserStatus("PENDING");
            dto.setImage(null);
            response.add(dto);
        }

        return response;
    }

    public void inviteUser(UserInviteRequestDto request) {

        Optional<UserInvite> existing =
                userInviteRepository.findByMobileAndIsAcceptedFalse(request.getMobileNumber());

        if (existing.isPresent()) {
            // 🔁 RESEND
            UserInvite invite = existing.get();

            invite.setInviteToken(UUID.randomUUID().toString());
            invite.setExpiresAt(LocalDateTime.now().plusDays(2));

            if (request.getRoles() != null && !request.getRoles().isEmpty()) {
                invite.setRoles(request.getRoles());
            }

            userInviteRepository.save(invite);
            return;
        }

        // 🆕 NEW INVITE
        UserInvite invite = new UserInvite();
        invite.setName(request.getName());
        invite.setMobile(request.getMobileNumber());
        invite.setAccommodationId(request.getAccommodationId());
        invite.setRoles(
                request.getRoles() != null ? request.getRoles() : List.of("STAFF")
        );
        invite.setIsAccepted(false);
        invite.setInviteToken(UUID.randomUUID().toString());
        invite.setExpiresAt(LocalDateTime.now().plusDays(2));

        userInviteRepository.save(invite);
    }

    public List<String> getRoleConstants() {
        return Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public void updateStaff(UpdateStaffDto request) {

        User user = userRepository.findById(Long.valueOf(request.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRoles(request.getRoles());

        userRepository.save(user);
    }
}
