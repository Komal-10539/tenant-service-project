package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class UserInvite {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    private String mobile;
    private String name;
    private String accommodationId;
    private String inviteToken;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    private Boolean isAccepted = false;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private String status;
}
