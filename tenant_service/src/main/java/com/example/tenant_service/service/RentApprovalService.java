package com.example.tenant_service.service;

import com.example.tenant_service.dto.RentApprovalResponse;
import com.example.tenant_service.dto.RentHistoryResponse;
import com.example.tenant_service.dto.RentPaymentDto;
import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RentApprovalService {
    private final RentRepository rentRepository;

    public Page<?> getApproval(String accommodationId, RentStatus status, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Rent> rents;

        if (status != null) {
            rents = rentRepository
                    .findByUser_Hostel_AccommodationIdAndStatus(
                            accommodationId,
                            status,
                            pageable
                    );

        return rents.map(rent -> {

            RentApprovalResponse dto = new RentApprovalResponse();
            dto.setInvoiceId(rent.getInvoiceId());
            dto.setTenantName(rent.getUser().getName());
            dto.setRoomNumber("101");
            dto.setDueDate(rent.getDueDate());
            dto.setPaidAt(rent.getPaidAt() != null ? LocalDate.parse(rent.getPaidAt()) : null);
            dto.setRentAmount(rent.getAmount());
            dto.setLateCharges(rent.getLateCharges() != null ? rent.getLateCharges() : 0.0);
            dto.setElectricityCharges(rent.getElectricityCharges() != null ? rent.getElectricityCharges() : 0.0);
            dto.setStatus(rent.getStatus().name());
            dto.setDocLink("http://localhost:8080/tenant-service/api/v1/tenant/rent/proof/" + rent.getInvoiceId());

            return dto;
        });
    }

        rents = rentRepository.findByUser_Hostel_AccommodationId(accommodationId, pageable);

        return rents.map(rent -> {

            RentPaymentDto dto = new RentPaymentDto();
            dto.setInvoiceId(rent.getInvoiceId());
            dto.setDueDate(rent.getDueDate().toString());
            dto.setPaidAt(rent.getPaidAt() != null ? rent.getPaidAt().toString() : null);
            dto.setRentAmount(rent.getAmount());
            dto.setLateCharges(rent.getLateCharges() != null ? rent.getLateCharges() : 0.0);
            dto.setElectricityCharges(rent.getElectricityCharges() != null ? rent.getElectricityCharges() : 0.0);
            dto.setStatus(rent.getStatus().name());

            return dto;
        });
    }
}
