package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "recent_activities")
@Data
public class RecentActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String type;

    private String username;
    private Long accommodationId;
    private LocalDateTime createdAt;
}