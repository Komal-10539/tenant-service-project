package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String accommodationId;
    private String accommodationName;

    @OneToMany(mappedBy = "hostel")
    private List<Room> rooms;

}