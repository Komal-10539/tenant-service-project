package com.example.tenant_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int day;
    @ElementCollection
    private List<String> breakfastItems;

    private String breakfastTiming;

    @ElementCollection
    private List<String> lunchItems;

    private String lunchTiming;

    @ElementCollection
    private List<String> dinnerItems;

    private String dinnerTiming;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;
}