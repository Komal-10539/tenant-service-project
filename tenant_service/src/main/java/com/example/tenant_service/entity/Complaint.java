package com.example.tenant_service.entity;

import com.example.tenant_service.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complaintNumber;
    private String subject;
    private String description;
    private String issueType;

    @Column(name = "accommodation_id")
    private String accommodationId;
    private String roomId;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    private boolean hasAttachment;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintDocument> documents = new ArrayList<>();
}

