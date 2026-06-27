package com.example.tenant_service.dto;

import lombok.Data;

import java.util.List;
@Data
public class TenantDashboardResponse {

    private String tenantName;
    private String roomNumber;
    private Long accommodationId;
    private String accommodationName;

    private MenuOfTheDayDto menuOfTheDay;
    private TenantStatusDto tenantStatus;
    private RentDetailsDto rentDetails;
    private List<RecentComplaintDto> recentComplaints;
}