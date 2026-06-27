package com.example.tenant_service.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.repository.UserRepository;
import com.example.tenant_service.utility.JwtUtil;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);


    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository,
                       JwtUtil jwtUtil,PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    public String login(User user) {
        List<User> dbUser =userRepository.findAllByUserName(user.getUserName());

        if (dbUser.isEmpty()) {
            log.info("User not found. Registering new user: {}", user.getUserName());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setName(user.getName());
            user.setRole("TENANT");
            userRepository.save(user);

            return jwtUtil.generateToken(user.getUserName(),user.getRole());
        }

        User savedUser = dbUser.get(0);

        if (!encoder.matches(user.getPassword(), savedUser.getPassword())) {
            log.warn("Invalid password attempt for user: {}", user.getUserName());
            throw new RuntimeException("WRONG PASSWORD");
        }
        log.info("User authenticated successfully: {}", user.getUserName());

        return jwtUtil.generateToken(savedUser.getUserName(), savedUser.getRole());
    }
}

