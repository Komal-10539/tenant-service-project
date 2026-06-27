package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.TenantDashboardResponse;
import com.example.tenant_service.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Response> getDashboard(Authentication authentication) {
        String username = authentication.getName();
        TenantDashboardResponse response = dashboardService.getDashboard(username);

        return ResponseEntity.ok(
                new Response("0000", "SUCCESS", true, response)
        );
    }
}
