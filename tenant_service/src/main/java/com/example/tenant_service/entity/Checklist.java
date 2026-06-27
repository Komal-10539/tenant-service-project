package com.example.tenant_service.entity;

import com.example.tenant_service.enums.ChecklistStatus;
import com.example.tenant_service.enums.ChecklistType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accommodationId;

    @Enumerated(EnumType.STRING)
    private ChecklistType type;

    @Enumerated(EnumType.STRING)
    private ChecklistStatus status;

    // getters and setters
}
