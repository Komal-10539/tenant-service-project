package com.example.tenant_service.service;

import com.example.tenant_service.dto.RecordResponseDto;
import com.example.tenant_service.dto.SaveRecordDto;
import com.example.tenant_service.entity.Room;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.RoomRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public RecordResponseDto getRecord(String accommodationId, Long tenantID){
        User user=userRepository.findByHostel_AccommodationIdAndId(accommodationId, tenantID);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return convertToDto(user);
    }

    private RecordResponseDto convertToDto(User user){
        RecordResponseDto dto = new RecordResponseDto();

        dto.setDueDate(user.getDueDate());
        dto.setInitialMeterReading(user.getInitialMeterReading());
        dto.setJoiningDate(user.getJoiningDate());
        dto.setNoticeEndDate(user.getNoticeEndDate());
        dto.setNoticeStartDate(user.getNoticeStartDate());
        dto.setNoticeStatus(user.getNoticeStatus());
        dto.setPerDayLateCharge(user.getPerDayLateCharge());
        dto.setRentAmount(user.getRentAmount());
        dto.setRoomId(user.getRoom().getRoomId());
        dto.setSecurityDeposit(user.getSecurityDeposit());
        dto.setStatus(user.getStatus());
        dto.setTenantId(user.getId());
        dto.setRoomNumber(user.getRoom().getRoomNumber());
        dto.setOnNotice(user.isOnNotice());

        return dto;
    }

    public SaveRecordDto updateRecord(SaveRecordDto request) {

        User user = userRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Room previousRoom = user.getRoom();

        if (previousRoom != null) {

            previousRoom.setRoomCapacityFilled(
                    previousRoom.getRoomCapacityFilled() - 1
            );

            roomRepository.save(previousRoom);
        }

        if (room.getRoomCapacityFilled() >= room.getRoomCapacity()) {
            throw new RuntimeException("Room is already full");
        }

        room.setRoomCapacityFilled(
                room.getRoomCapacityFilled() + 1
        );

        roomRepository.save(room);

       user.setRoom(room);

        user.setJoiningDate(request.getJoiningDate());
        user.setDueDate(request.getDueDate().toString());
        user.setInitialMeterReading(request.getInitialMeterReading());
        user.setRentAmount(String.valueOf(request.getRentAmount()));
        user.setStatus(request.getStatus());

        userRepository.save(user);

        return convertToSaveRecordDto(user);
    }

    private SaveRecordDto convertToSaveRecordDto(User user1){
        SaveRecordDto dto = new SaveRecordDto();

        //dto.setDueDate(LocalDate.parse(user1.getDueDate()));
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        dto.setDueDate(LocalDate.parse(user1.getDueDate(), formatter));
        dto.setInitialMeterReading(user1.getInitialMeterReading());
        dto.setJoiningDate(user1.getJoiningDate());
        dto.setRentAmount(Double.parseDouble(user1.getRentAmount()));
        dto.setStatus(user1.getStatus());
        dto.setTenantId(user1.getId());
        if(user1.getRoom()!=null){
            dto.setRoomId(String.valueOf(user1.getRoom().getRoomId()));
            dto.setRoomNumber(user1.getRoom().getRoomNumber());
        }
        //dto.setRoomNumber(user1.getRoom().getRoomNumber());

        return dto;
    }

}
