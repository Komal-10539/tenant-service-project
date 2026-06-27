package com.example.tenant_service.controller;

import com.example.tenant_service.dto.ManagerDashboardResponse;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.RoomResponse;
import com.example.tenant_service.service.ManagerDashboardService;
import com.example.tenant_service.service.RoomManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tenant-service/api/v1/manager")
@RequiredArgsConstructor
public class RoomManagementController {
   private final RoomManagementService roomManagementService;

    @GetMapping("/room/list")
    public ResponseEntity<Response> getRoomList(
            @RequestParam String accommodationId) {

        List<RoomResponse> rooms =
                roomManagementService.getRoomsByAccommodation(accommodationId);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true,rooms)
        );

    }
}
