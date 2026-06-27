package com.example.tenant_service.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user,HttpServletResponse response)throws Exception {

        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            log.warn("Login failed: Username is missing");
            return ResponseEntity
                    .badRequest()
                    .body("Username is required");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            log.warn("Login failed: Password is missing for user: {}", user.getUserName());
            return ResponseEntity
                    .badRequest()
                    .body("Password is required");
        }

        String token=authService.login(user);
        log.info("Login successful for user: {}", user.getUserName());
        Response resp =new Response("0000", "SUCCESS", true, token);
        return ResponseEntity.ok(resp);
    }
}
