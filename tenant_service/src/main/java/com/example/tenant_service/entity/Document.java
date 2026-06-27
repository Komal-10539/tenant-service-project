package com.example.tenant_service.entity;

import com.example.tenant_service.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
@RequiredArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accommodationId;

    @Column(name = "docName", nullable = false)
    private String docName;

    private String docLink;

    @Lob
    @Column(name = "doc_file",columnDefinition = "LONGBLOB")
    private byte[] docFile;

    private String contentType;


    @Column(name = "required", nullable = false)
    private Boolean required;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    private LocalDateTime uploadDate;

    private String uploadedBy;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
