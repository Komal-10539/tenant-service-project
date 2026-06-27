package com.example.tenant_service.controller;

import com.example.tenant_service.dto.DetailResponseDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/tenant")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;

    @GetMapping("/details")
    public ResponseEntity<Response> getDetails(
            @RequestParam String accommodationId,
            @RequestParam Long tenantId
    ){
        DetailResponseDto detail=detailService.getDetails(accommodationId,tenantId);

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                detail
        );
        return ResponseEntity.ok(response);
    }

}
