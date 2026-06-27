package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.StaffResponseDto;
import com.example.tenant_service.dto.UpdateStaffDto;
import com.example.tenant_service.dto.UserInviteRequestDto;
import com.example.tenant_service.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tenant-service/api/v1")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/manager/user")
    public ResponseEntity<Response> getStaff(
            @RequestParam String accommodationId
    ) {

        List<StaffResponseDto> staff = staffService.getStaff(accommodationId);

        Map<String, Object> data = new HashMap<>();
        data.put("data", staff);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, data)
        );
    }

    @PostMapping("/manager/user/invite")
    public ResponseEntity<Response> inviteUser(
            @RequestBody UserInviteRequestDto request
    ) {
        staffService.inviteUser(request);

        return ResponseEntity.ok(
                new Response("INFO000", "Invite sent successfully", true, null)
        );
    }

    @GetMapping("/user/role/constants")
    public ResponseEntity<Response> getRoleConstants() {

        List<String> roles = staffService.getRoleConstants();

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, roles)
        );
    }

    @PutMapping("/manager/user")
    public ResponseEntity<Response> updateStaff(
            @RequestBody UpdateStaffDto request
    ) {
        staffService.updateStaff(request);

        return ResponseEntity.ok(
                new Response("INFO000", "Staff updated successfully", true, null)
        );
    }
}
