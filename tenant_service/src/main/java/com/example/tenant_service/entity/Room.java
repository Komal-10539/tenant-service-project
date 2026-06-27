package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roomId;

    private String roomNumber;
    private Integer roomCapacity;
    private Integer roomCapacityFilled;
    private String remark;
    private Double rentAmount;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;

    @OneToMany(mappedBy = "room")
    private List<User> users;


}