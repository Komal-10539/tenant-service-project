package com.example.tenant_service.controller;

import com.example.tenant_service.dto.Response;
import com.example.tenant_service.dto.UserAccommodationDto;
import com.example.tenant_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/role/mapping")
    public ResponseEntity<Response> getUserAccommodations() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        List<UserAccommodationDto> data =
                userService.getUserAccommodations(username);

        Response response = new Response();
        response.setCode("INFO000");
        response.setMessage("SUCCESS");
        response.setData(data);

        return ResponseEntity.ok(response);
    }
}


