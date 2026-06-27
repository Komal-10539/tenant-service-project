package com.example.tenant_service.entity;

import com.example.tenant_service.enums.PaymentMode;
import com.example.tenant_service.enums.RentStatus;
import jakarta.persistence.*;
import lombok.Data;

import javax.print.DocFlavor;
import java.time.LocalDate;
@Data
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private Double paidAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private RentStatus status;

    private String invoiceId;
    private String paidAt;
    private Double rentAmount;
    private Double lateCharges;
    private Double electricityCharges;
    @Lob
    @Column(name = "proof_image")
    private byte[] proofImage;

    private String proofType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}