package com.example.tenant_service.controller;

import com.example.tenant_service.dto.RecentComplaintDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.TenantListDto;
import com.example.tenant_service.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping("/list")
    public ResponseEntity<Response> getTenants(
            @RequestParam String accommodationId,
            @RequestParam(defaultValue = "0") int page

    ) {
        Page<TenantListDto> tenantPage =
                tenantService.getTenants(accommodationId, page);

        Map<String, Object> data = new HashMap<>();
        data.put("content", tenantPage.getContent());
        data.put("number", tenantPage.getNumber());
        data.put("numberOfElements", tenantPage.getNumberOfElements());
        data.put("totalElements", tenantPage.getTotalElements());
        data.put("totalPages", tenantPage.getTotalPages());

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                data
        );

        return ResponseEntity.ok(response);
    }
}
