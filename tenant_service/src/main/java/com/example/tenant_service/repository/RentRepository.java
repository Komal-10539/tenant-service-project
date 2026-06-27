package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.PaymentMode;
import com.example.tenant_service.enums.RentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUser(User user);
    boolean existsByUserAndDueDateBetween(User user,
                                          LocalDate start,
                                          LocalDate end);
    Page<Rent> findByUser_Hostel_AccommodationIdAndStatusAndDueDateGreaterThanEqual(
            String accommodationId,
            RentStatus status,
            LocalDate today,
            Pageable pageable
    );
    Rent findByInvoiceId(String invoiceId);

    Page<Rent> findByUser_Hostel_AccommodationIdAndStatus(
            String accommodationId, RentStatus rentStatus, Pageable pageable);
    Rent findByUser_Hostel_AccommodationIdAndInvoiceIdAndPaymentModeAndStatus(
            String accommodationId, String invoiceId, PaymentMode paymentMode, RentStatus status
    );

    List<Rent> findByStatus(RentStatus rentStatus);
    Page<Rent> findByUser_Hostel_AccommodationIdAndStatusAndDueDateLessThan(
            String accommodationId,
            RentStatus status,
            LocalDate today,
            Pageable pageable
    );
    Page<Rent> findByUser_Hostel_AccommodationId(
            String accommodationId,
            Pageable pageable
    );
    List<Rent> findByUser_Hostel_AccommodationId(
            String accommodationId
    );

    long countByUser_Hostel_AccommodationIdAndStatus(String accommodationId, RentStatus status);

}
