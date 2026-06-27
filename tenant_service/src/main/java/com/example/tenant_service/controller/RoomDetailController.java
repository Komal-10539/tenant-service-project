package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.RoomDetailResponse;
import com.example.tenant_service.service.RoomDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant-service/api/v1/manager")
@RequiredArgsConstructor
public class RoomDetailController {
    private final RoomDetailService roomDetailService;

        @GetMapping("/room")
        public ResponseEntity<Response> getRoomDetails(
                @RequestParam String accommodationId,
                @RequestParam String roomId) {

            RoomDetailResponse response =
                    roomDetailService.getRoomDetails(accommodationId, roomId);

            return ResponseEntity.ok(
                    new Response("INFO000", "SUCCESS", true, response)
            );
        }

}
