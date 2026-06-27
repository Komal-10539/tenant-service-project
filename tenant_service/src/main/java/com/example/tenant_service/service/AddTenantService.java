package com.example.tenant_service.service;

import com.example.tenant_service.dto.TenantInviteRequest;
import com.example.tenant_service.dto.UserInviteRequestDto;
import com.example.tenant_service.entity.UserInvite;
import com.example.tenant_service.repository.UserInviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddTenantService {
    private final UserInviteRepository userInviteRepository;

    public void inviteTenant(TenantInviteRequest request) {

        Optional<UserInvite> existing =
                userInviteRepository.findByMobileAndAccommodationId( request.getMobile(),
                        request.getAccommodationId());

        if (existing.isPresent()) {
            // 🔁 RESEND
            UserInvite invite = existing.get();

            invite.setInviteToken(UUID.randomUUID().toString());
            invite.setExpiresAt(LocalDateTime.now().plusDays(2));

            userInviteRepository.save(invite);
            return;
        }
        UserInvite invite = new UserInvite();
        invite.setMobile(request.getMobile());
        invite.setAccommodationId(request.getAccommodationId()); // ✅ string directly
        invite.setInviteToken(invite.getInviteToken());
        invite.setStatus("PENDING");
        invite.setCreatedAt(LocalDateTime.now());

        userInviteRepository.save(invite);
      }
    }
