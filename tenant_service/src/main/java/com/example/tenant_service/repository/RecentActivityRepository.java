package com.example.tenant_service.repository;

import com.example.tenant_service.entity.RecentActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long> {

    List<RecentActivity> findTop10ByAccommodationIdOrderByCreatedAtDesc(Long accommodationId);
}
