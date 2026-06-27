package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.TenantInviteRequest;
import com.example.tenant_service.service.AddTenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/tenant")
@RequiredArgsConstructor
public class AddTenantController {

    private final AddTenantService addTenantService;

    @PostMapping("/invite")
    public ResponseEntity<Response> inviteTenant(@RequestBody TenantInviteRequest request) {

        addTenantService.inviteTenant(request);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, null)
        );
    }
}
