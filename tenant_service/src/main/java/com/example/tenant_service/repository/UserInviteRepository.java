package com.example.tenant_service.repository;

import com.example.tenant_service.entity.UserInvite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInviteRepository extends JpaRepository<UserInvite, String> {

    List<UserInvite> findByAccommodationIdAndIsAcceptedFalse(String accommodationId);
    Optional<UserInvite> findByMobileAndIsAcceptedFalse(String mobile);

        Optional<UserInvite> findByMobileAndAccommodationId(String mobile, String accommodationId);


  //  Optional<UserInvite> findByMobileAndAccommodationId(String mobile, UUID uuid);
}
