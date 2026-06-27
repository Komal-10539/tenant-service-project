package com.example.tenant_service.controller;

import com.example.tenant_service.dto.ProfileResponse;
import com.example.tenant_service.dto.Response;
import com.example.tenant_service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/v1/tenant/self/onboarding")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/details")
    public ResponseEntity<Response> getProfile(@RequestParam String accommodationId){
        ProfileResponse response =
                profileService.getprofile(accommodationId);
        return ResponseEntity.ok(
       new Response("INFO000","SUCCESS",true,response) );
    }

    @PostMapping("/details/update")
    public ResponseEntity<Response> getProfileupdate(){
        ProfileResponse response =
                profileService.getprofileupdate();
        return ResponseEntity.ok(
                new Response("INFO000","SUCCESS",true,response) );
    }

}
