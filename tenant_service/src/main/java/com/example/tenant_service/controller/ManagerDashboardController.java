package com.example.tenant_service.controller;

import com.example.tenant_service.dto.ManagerDashboardResponse;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.service.ManagerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager/")
@RequiredArgsConstructor
public class ManagerDashboardController {
    private final ManagerDashboardService managerDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<Response> getManagerDashboard(Authentication authentication) {
        String username = authentication.getName();
        ManagerDashboardResponse response = managerDashboardService.getDashboard(username);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, response)
        );
    }
}
