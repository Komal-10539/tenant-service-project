package com.example.tenant_service.repository;

import java.util.List;
import java.util.Optional;

import com.example.tenant_service.entity.Complaint;
import com.example.tenant_service.entity.Hostel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tenant_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserName(String userName);
    List<User> findByRoom_RoomId(String roomId);
    List<User> findByHostel_AccommodationId(String accommodationId);
    Page<User> findAll(Pageable pageable);
    Page<User> findByHostel_AccommodationIdAndRole(
            String accommodationId,
            String role,
            Pageable pageable
    );
    User findByHostel_AccommodationIdAndId(
            String accommodationId,
            Long Id
    );
    Optional<User> findFirstByUserNameAndHostel_AccommodationId(
            String userName,
            String accommodationId
    );
    List<User> findByUserName(String userName);

    List<User> findByRole(String role);
    long countByHostel_AccommodationIdAndRoomIsNull(String accommodationId);


    Optional<User> findByMobileNumber(String mobileNumber);
}
