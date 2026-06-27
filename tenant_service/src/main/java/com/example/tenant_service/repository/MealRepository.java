package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Hostel;
import com.example.tenant_service.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findTopByHostelOrderByIdDesc(Hostel hostel);
    //List<Meal> findByAccommodationIdOrderByDayAsc(String accommodationId );
    List<Meal> findByHostel_IdOrderByDayAsc(Long hostelId);
    //List<Meal> findByHostel_AccommodationIdOrderByDayAsc(String accommodationId);

}