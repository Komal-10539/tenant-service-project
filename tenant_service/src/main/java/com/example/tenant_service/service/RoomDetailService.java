package com.example.tenant_service.service;

import com.example.tenant_service.dto.RoomDetailResponse;
import com.example.tenant_service.dto.TenantDetailResponse;
import com.example.tenant_service.entity.Room;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.RoomRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomDetailService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomDetailResponse getRoomDetails(String accommodationId, String roomId) {

        Long Id = Long.parseLong(accommodationId);

        Room room = roomRepository.findByRoomIdAndHostel_Id(roomId, Id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        List<User> users = userRepository.findByRoom_RoomId(roomId);

        List<TenantDetailResponse> tenantList = users.stream()
                .map(user -> TenantDetailResponse.builder()
                        .tenantId(String.valueOf(user.getId()))
                        .tenantName(user.getName())
                        .mobileNumber(user.getUserName())
                        .aadhaarVerified(user.isAadhaarVerified())
                        .advanceKycVerified(user.isAdvanceKycVerified())
                        .docVerified(user.isDocVerified())
                        .status(user.getStatus())
                        .build())
                .toList();

        boolean isRoomAvailable = room.getRoomCapacityFilled() < room.getRoomCapacity();

        return RoomDetailResponse.builder()
                .roomId(room.getRoomId())
                .roomNumber(room.getRoomNumber())
                .roomCapacity(room.getRoomCapacity())
                .roomCapacityFilled(room.getRoomCapacityFilled())
                .isRoomAvailable(isRoomAvailable)
                .remark(room.getRemark())
                .tenantDetailList(tenantList)
                .build();
        }
}
