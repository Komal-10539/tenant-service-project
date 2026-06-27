package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.WeeklyMenuDto;
import com.example.tenant_service.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tenant-service/api/v1/tenant/food/menu")
@RequiredArgsConstructor
public class TenantFoodController {

    private final MealService mealService;

    @GetMapping()
    public ResponseEntity<?> getMenu(
            @RequestParam(required = false) String accommodationId,
            Authentication authentication) {
        String username = authentication.getName();
        List<WeeklyMenuDto> menu =
                mealService.getWeeklyMenu(accommodationId, username);

        Response response = new Response(
                "INFO000",
                "SUCCESS",
                true,
                menu
        );
        return ResponseEntity.ok(response);
    }
}
