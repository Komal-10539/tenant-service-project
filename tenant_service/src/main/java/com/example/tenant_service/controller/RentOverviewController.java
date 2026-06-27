package com.example.tenant_service.controller;

import com.example.tenant_service.dto.CollectionStatusDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.service.RentOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tenant-service/api/v1/manager/rent")
@RequiredArgsConstructor
public class RentOverviewController {
    private final RentOverviewService rentOverviewService;

    @GetMapping("/collection/status")
    public ResponseEntity<Response> getCollectionStatus(
            @RequestParam String accommodationId
    ) {

        CollectionStatusDto response =
                rentOverviewService.getCollectionStatus(accommodationId);

        return ResponseEntity.ok(
                new Response("INFO000", "SUCCESS", true, response)
        );
    }
}
