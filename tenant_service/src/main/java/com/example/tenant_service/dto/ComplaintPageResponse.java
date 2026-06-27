package com.example.tenant_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class ComplaintPageResponse {

    private List<RecentComplaintDto> data;
    private int page;
    private boolean hasNext;

}
