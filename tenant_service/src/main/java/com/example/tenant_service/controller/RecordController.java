package com.example.tenant_service.controller;

import com.example.tenant_service.dto.DetailResponseDto;
import com.example.tenant_service.dto.RecordResponseDto;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.SaveRecordDto;
import com.example.tenant_service.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/v1/manager/rent")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/record")
    public ResponseEntity<Response> getRecord(
            @RequestParam String accommodationId,
            @RequestParam Long tenantId
    ) {
        RecordResponseDto record = recordService.getRecord(accommodationId, tenantId);

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                record
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/record/update")
    public ResponseEntity<Response> getRecordSave(
            @RequestBody SaveRecordDto request) {

        SaveRecordDto response =
                recordService.updateRecord(request);

        return ResponseEntity.ok(
                new Response(
                        "INFO000",
                        "SUCCESS",
                        true,
                        response
                )
        );
    }

}
