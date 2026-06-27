package com.example.tenant_service.service;

import com.example.tenant_service.dto.RoomResponse;
import com.example.tenant_service.entity.Room;
import com.example.tenant_service.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomManagementService {
    private final RoomRepository roomRepository;
    public List<RoomResponse> getRoomsByAccommodation(String accommodationId) {

        Long hostelId = Long.parseLong(accommodationId);

        List<Room> rooms = roomRepository.findByHostelId(hostelId);

        return rooms.stream().map(room -> {
            boolean isRoomAvailable = room.getRoomCapacityFilled() < room.getRoomCapacity();

            return RoomResponse.builder()
                    .roomId(room.getRoomId())
                    .roomNumber(room.getRoomNumber())
                    .roomCapacity(room.getRoomCapacity())
                    .roomCapacityFilled(room.getRoomCapacityFilled())
                    .isRoomAvailable(isRoomAvailable)
                    .remark(room.getRemark() != null ? room.getRemark() : "")
                    .build();
        }).toList();
    }

}
