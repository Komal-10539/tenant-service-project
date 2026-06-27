package com.example.tenant_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManagerDashboardResponse {
    private String tenantName;
    private String roomNumber;
    private Long accommodationId;
    private String accommodationName;

    private OccupancyStatusDto occupancyStatus;
    private CollectionStatusDto collectionStatus;
    private List<ActionItemDto> actionItems;
    private List<RecentActivitiesDto> recentActivities;
}
