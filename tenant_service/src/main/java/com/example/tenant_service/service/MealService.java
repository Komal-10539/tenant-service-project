package com.example.tenant_service.service;

import com.example.tenant_service.dto.WeeklyMenuDto;
import com.example.tenant_service.entity.Hostel;
import com.example.tenant_service.entity.Meal;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.HostelRepository;
import com.example.tenant_service.repository.MealRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final HostelRepository hostelRepository;
    private final UserRepository userRepository;


    public List<WeeklyMenuDto> getWeeklyMenu(String accommodationId, String username) {
        Hostel hostel;

        if (accommodationId.matches("\\d+")) {

            Long hostelId = Long.valueOf(accommodationId);

            hostel = hostelRepository.findById(hostelId)
                    .orElseThrow(() -> new RuntimeException("Hostel not found"));

        } else {

            hostel = hostelRepository.findByAccommodationId(accommodationId)
                    .orElseThrow(() -> new RuntimeException("Hostel not found"));
        }
        List<Meal> meals = mealRepository.findByHostel_IdOrderByDayAsc(hostel.getId());

        return meals.stream().map(meal -> {

            WeeklyMenuDto dto = new WeeklyMenuDto();
            dto.setDay(meal.getDay());
            dto.setBreakfastItems(meal.getBreakfastItems());
            dto.setBreakfastTiming(meal.getBreakfastTiming());
            dto.setLunchItems(meal.getLunchItems());
            dto.setLunchTiming(meal.getLunchTiming());
            dto.setDinnerItems(meal.getDinnerItems());
            dto.setDinnerTiming(meal.getDinnerTiming());

            return dto;

        }).toList();
    }
}