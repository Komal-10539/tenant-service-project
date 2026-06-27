package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostelRepository extends JpaRepository<Hostel, Long> {

    // Find hostel by name
    Optional<Hostel> findByName(String name);

    // Optional: find by address
    Optional<Hostel> findByAddress(String address);
    Optional<Hostel> findByAccommodationId(String accommodationId);
}
