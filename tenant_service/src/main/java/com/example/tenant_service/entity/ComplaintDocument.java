package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ComplaintDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;

    @ManyToOne
    @JoinColumn(name = "complaint_id",nullable=false)
    private Complaint complaint;
}