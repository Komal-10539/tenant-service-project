package com.example.tenant_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class MenuOfTheDayDto {

    private int day;

    private List<String> breakfastItems;
    private String breakfastTiming;

    private List<String> lunchItems;
    private String lunchTiming;

    private List<String> dinnerItems;
    private String dinnerTiming;

}