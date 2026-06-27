package com.example.tenant_service.dto;

public class ChecklistResponse {

    private String status;

    public ChecklistResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
