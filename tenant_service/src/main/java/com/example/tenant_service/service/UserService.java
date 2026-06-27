package com.example.tenant_service.service;

import com.example.tenant_service.dto.UserAccommodationDto;
import com.example.tenant_service.entity.Hostel;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.Role;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

 private final UserRepository userRepository;

 public List<UserAccommodationDto> getUserAccommodations(String userName){
     List<User> users = userRepository.findAllByUserName(userName);

     if (users.isEmpty()) {
         throw new RuntimeException("User not found");
     }

     return users.stream()
             .map(user -> UserAccommodationDto.builder()
                     .accommodationId(user.getHostel().getId().toString())
                     .accommodationName(user.getHostel().getName())
                     .role(user.getRole())
                     .build()
             )
             .toList();
 }

 }

