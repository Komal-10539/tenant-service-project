package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@Table()
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private String name;
    private String role;
    private LocalDateTime createdAt;
    private String aadhaarVerifiedName;
    private String accommodationDetail;
    private String careOf;
    private String dob;
    private String emailId;
    private String employmentNature;
    private String gender;
    private String localRef;
    private String localRef1;
    private String localRef2;
    private String localRefMobile;
    private String localRefRelation;
    private String mobileNumber;
    private String occupation;
    private String officeAddress;
    private String officeNumber;
    private String workPlace;
    private String permanentAddress;
    private String localAddress;
    private String pinCode;
    private String rentalRecord;
    private String uuid;
    private List<String> editableFields;
    private String dueDate;
    private String initialMeterReading;
    private boolean isOnNotice;
    private LocalDate joiningDate;
    private String noticeEndDate;
    private String noticeStartDate;
    private String noticeStatus;
    private String perDayLateCharge;
    private String rentAmount;
    private String securityDeposit;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    private Boolean isActive;

    private String image;

    private boolean mobileVerified;
    private boolean aadhaarVerified;
    private boolean detailsSubmitted;
    private boolean documentsUploaded;
    private boolean detailsVerified;
    private boolean docVerified;
    private boolean advanceKycVerified;
    private String status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;
}





