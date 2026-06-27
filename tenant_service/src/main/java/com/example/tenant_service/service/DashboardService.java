package com.example.tenant_service.service;

import com.example.tenant_service.dto.*;
import com.example.tenant_service.entity.Complaint;
import com.example.tenant_service.entity.Meal;
import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.repository.ComplaintRepository;
import com.example.tenant_service.repository.MealRepository;
import com.example.tenant_service.repository.RentRepository;
import com.example.tenant_service.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final MealRepository mealRepository;
    private final ComplaintRepository complaintRepository;

    public DashboardService(UserRepository userRepository,
                            RentRepository rentRepository,
                            MealRepository mealRepository,
                            ComplaintRepository complaintRepository) {
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
        this.mealRepository = mealRepository;
        this.complaintRepository = complaintRepository;
    }
    public TenantDashboardResponse getDashboard(String username) {
        List<User> users = userRepository.findAllByUserName(username);
        User user = users.get(0);

        //User user = userRepository.findByUserName(username)
                //.orElseThrow(() -> new RuntimeException("User not found"));

        TenantDashboardResponse response = new TenantDashboardResponse();

        // HEADER
        response.setTenantName(user.getName());
        response.setRoomNumber(user.getRoom().getRoomNumber());
        response.setAccommodationId(user.getRoom().getHostel().getId());
        response.setAccommodationName(user.getRoom().getHostel().getName());

        // TENANT STATUS
        TenantStatusDto statusDto = new TenantStatusDto();
        statusDto.setMobileVerified(user.isMobileVerified());
        statusDto.setAadhaarVerified(user.isAadhaarVerified());
        statusDto.setDetailsSubmitted(user.isDetailsSubmitted());
        statusDto.setDocumentsUploaded(user.isDocumentsUploaded());
        statusDto.setDetailsVerified(user.isDetailsVerified());

        response.setTenantStatus(statusDto);

        // RENT
        List<Rent> rents = rentRepository.findByUser(user);

        double totalDues = rents.stream()
                .filter(r -> r.getStatus() == RentStatus.UNPAID)
                .mapToDouble(r -> r.getAmount() - r.getPaidAmount())
                .sum();

        RentDetailsDto rentDto = new RentDetailsDto();
        rentDto.setTotalDues(totalDues);
        rentDto.setUpcomingRent(totalDues);
        rentDto.setDueDate(rents.isEmpty() ? null : (rents.get(0).getDueDate()));

        response.setRentDetails(rentDto);

        // MEAL
        Meal meal = mealRepository.findTopByHostelOrderByIdDesc(user.getRoom().getHostel());
        if (meal != null) {
            MenuOfTheDayDto mealDto = new MenuOfTheDayDto();

            mealDto.setDay(meal.getDay());

            mealDto.setBreakfastItems(meal.getBreakfastItems());
            mealDto.setBreakfastTiming(meal.getBreakfastTiming());

            mealDto.setLunchItems(meal.getLunchItems());
            mealDto.setLunchTiming(meal.getLunchTiming());

            mealDto.setDinnerItems(meal.getDinnerItems());
            mealDto.setDinnerTiming(meal.getDinnerTiming());

            response.setMenuOfTheDay(mealDto);
        }

        // COMPLAINT
        List<RecentComplaintDto> recentComplaints = new ArrayList<>();
        Complaint complaint = complaintRepository.findTopByUserOrderByCreatedAtDesc(user);

        if (complaint != null) {
            RecentComplaintDto complaintDto = new RecentComplaintDto();

            complaintDto.setComplainType(complaint.getSubject()); // or type
            complaintDto.setComplaintNumber(complaint.getComplaintNumber());
            complaintDto.setDescription(complaint.getSubject()); // if no description field
            complaintDto.setHasAttachment(false); // if not stored
            complaintDto.setRoomId(user.getRoom().getRoomNumber());
            complaintDto.setStatus(complaint.getStatus().name());
            recentComplaints.add(complaintDto);

        }
        response.setRecentComplaints(recentComplaints);
        return response;
    }

    }