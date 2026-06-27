package com.example.tenant_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {

    private Boolean isRoomAvailable;
    private String remark;
    private int roomCapacity;
    private int roomCapacityFilled;
    private String roomId;
    private String roomNumber;
}
