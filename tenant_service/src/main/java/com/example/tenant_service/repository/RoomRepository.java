package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Room;
import com.example.tenant_service.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("SELECT r FROM Room r WHERE r.hostel.id = :hostelId")
    List<Room> findByHostelId(@Param("hostelId") Long hostelId);

    Optional<Room> findByRoomIdAndHostel_Id(String roomId, Long Id);

}
