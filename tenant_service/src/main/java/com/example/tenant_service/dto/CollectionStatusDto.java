package com.example.tenant_service.dto;

import lombok.Data;

@Data
public class CollectionStatusDto {

    private String accommodationName;
    private double collected;
    private double total;
    private double overdueAmount;
    private double approvalPending;

}
