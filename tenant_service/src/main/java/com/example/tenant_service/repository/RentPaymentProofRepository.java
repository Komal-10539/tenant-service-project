package com.example.tenant_service.repository;

import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.entity.RentPaymentProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentPaymentProofRepository extends JpaRepository<RentPaymentProof, Long> {

    Optional<RentPaymentProof> findTopByRentOrderByUploadedAtDesc(Rent rent);
    Optional<RentPaymentProof> findTopByRent_InvoiceIdOrderByUploadedAtDesc(String invoiceId);

}
