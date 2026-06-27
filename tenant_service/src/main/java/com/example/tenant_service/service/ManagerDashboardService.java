package com.example.tenant_service.service;

import com.example.tenant_service.dto.ActionItemDto;
import com.example.tenant_service.dto.ManagerDashboardResponse;
import com.example.tenant_service.dto.OccupancyStatusDto;
import com.example.tenant_service.dto.TenantDashboardResponse;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.HostelRepository;
import com.example.tenant_service.repository.RentRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerDashboardService {
    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final DummyDataService dummyDataService;
    private final DashboardService dashboardService;

    public ManagerDashboardResponse getDashboard(String userName){

        List<User> users = userRepository.findAllByUserName(userName);
        User user = users.get(0);

        if ("TENANT".equalsIgnoreCase(user.getRole())) {
            TenantDashboardResponse tenantRes= dashboardService.getDashboard(userName);
        }

        ManagerDashboardResponse response=new ManagerDashboardResponse();

        response.setTenantName(user.getName());
        response.setRoomNumber(user.getRoom().getRoomNumber());
        response.setAccommodationId(user.getRoom().getHostel().getId());
        response.setAccommodationName(user.getRoom().getHostel().getName());

        response.setOccupancyStatus(dummyDataService.getOccupancyStatus());
        response.setCollectionStatus(dummyDataService.getCollectionStatus());
        response.setActionItems(dummyDataService.getActionItems(response.getAccommodationName()));
        response.setRecentActivities(dummyDataService.getRecentActivities());
        //response.setActionItems(getActionItems(accommodationId));
       // response.setActionRequiredCount(response.getActionItems().size());

        return response;
    }


}
