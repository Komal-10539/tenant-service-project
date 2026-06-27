package com.example.tenant_service.service;

import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.enums.PaymentMode;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerifiedPaymentService {
    private final RentRepository rentRepository;

    public void verifyPayment(String accommodationId, String invoiceId, PaymentMode paymentMode, RentStatus status) {
        Rent rent = rentRepository.findByInvoiceId(invoiceId);

        if (rent == null) {
            throw new RuntimeException("Rent invoice not found");
        }

        if (rent.getStatus() != RentStatus.APPROVAL_PENDING) {
            throw new RuntimeException("Payment is not pending approval");
        }

            rent.setStatus(RentStatus.PAID);
            rent.setPaymentMode(paymentMode);
            rent.setPaidAt(String.valueOf(LocalDateTime.now()));

            rentRepository.save(rent);

        }
}
