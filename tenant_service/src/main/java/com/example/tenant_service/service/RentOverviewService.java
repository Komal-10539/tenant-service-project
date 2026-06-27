package com.example.tenant_service.service;

import com.example.tenant_service.dto.CollectionStatusDto;
import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentOverviewService {
    private final RentRepository rentRepository;

    public CollectionStatusDto getCollectionStatus(String accommodationId) {

        List<Rent> rents = rentRepository
                .findByUser_Hostel_AccommodationId(accommodationId);

        double collected = 0;
        double total = 0;
        double overdue = 0;
        double approvalPending = 0;

        for (Rent rent : rents) {

            double amount = rent.getAmount()
                    + (rent.getLateCharges() != null ? rent.getLateCharges() : 0)
                    + (rent.getElectricityCharges() != null ? rent.getElectricityCharges() : 0);

            total += amount;

            switch (rent.getStatus()) {

                case PAID:
                    collected += amount;
                    break;

                case OVERDUE:
                    overdue += amount;
                    break;

                case APPROVAL_PENDING:
                    approvalPending += amount;
                    break;

                default:
                    break;
            }
        }

        CollectionStatusDto res = new CollectionStatusDto();
        res.setCollected(collected);
        res.setTotal(total);
        res.setOverdueAmount(overdue);
        res.setApprovalPending(approvalPending);

        return res;
    }
}
