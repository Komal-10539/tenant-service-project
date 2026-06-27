package com.example.tenant_service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class RoomDetailResponse {
    private Boolean isRoomAvailable;
    private String remark;
    private int roomCapacity;
    private int roomCapacityFilled;
    private String roomId;
    private String roomNumber;
    private List<TenantDetailResponse> tenantDetailList;
}
