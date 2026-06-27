package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class RentPaymentProof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "rent_id")
    private Rent rent;

}