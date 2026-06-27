package com.example.tenant_service.service;

import com.example.tenant_service.dto.RentPaymentDto;
import com.example.tenant_service.dto.RentRecordDto;
import com.example.tenant_service.entity.Rent;
import com.example.tenant_service.entity.RentPaymentProof;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.repository.RentPaymentProofRepository;
import com.example.tenant_service.repository.RentRepository;
import com.example.tenant_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final UserRepository userRepository;
    private final RentRepository rentRepository;
    private final RentPaymentProofRepository rentPaymentProofRepository;
    private final RecentActivityService recentActivityService;

    public RentRecordDto getRentRecord(String accommodationId) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository
                .findFirstByUserNameAndHostel_AccommodationId(
                        username,
                        accommodationId
                )
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        RentRecordDto dto = new RentRecordDto();

        dto.setRoomNumber(user.getRoom().getRoomNumber());
        dto.setJoiningDate(user.getJoiningDate());
        dto.setRentAmount(user.getRoom().getRentAmount());

        return dto;
    }


    public Page<RentPaymentDto> getUpcomingRent(String accommodationId, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        Page<Rent> rents = rentRepository
                .findByUser_Hostel_AccommodationIdAndStatusAndDueDateGreaterThanEqual(
                        accommodationId,
                        RentStatus.UNPAID,
                        LocalDate.now(),
                        pageable
                );
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

    public Page<RentPaymentDto> getOverdue(String accommodationId, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        Page<Rent> rents = rentRepository
                .findByUser_Hostel_AccommodationIdAndStatusAndDueDateLessThan(
                        accommodationId,
                        RentStatus.OVERDUE,
                        LocalDate.now(),
                        pageable
                );
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

    public Page<RentPaymentDto> getRentHistory(String accommodationId, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        Page<Rent> rents = rentRepository
                .findByUser_Hostel_AccommodationId(
                        accommodationId,
                        pageable
                );

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
    @Transactional
    public void uploadRentProof(String invoiceId, List<MultipartFile> proofs) {

        Rent rent = rentRepository.findByInvoiceId(invoiceId);


        for (MultipartFile file : proofs) {

            try {

                RentPaymentProof proof = new RentPaymentProof();
                proof.setFileName(file.getOriginalFilename());
                proof.setFileType(file.getContentType());
                proof.setData(file.getBytes());
                proof.setUploadedAt(LocalDateTime.now());
                proof.setRent(rent);
                rent.setStatus(RentStatus.APPROVAL_PENDING);

                rentPaymentProofRepository.save(proof);

            } catch (IOException e) {
                throw new RuntimeException("File upload failed", e);
            }
        }

        rent.setStatus(RentStatus.APPROVAL_PENDING);
        rentRepository.save(rent);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Long accommodationId = Long.valueOf(rent.getUser().getHostel().getAccommodationId()); // based on your repo query

        recentActivityService.logActivity(
                "Payment initiated for Room " + rent.getUser().getRoom().getRoomNumber(),
                "PAYMENT_INITIATED",
                username,
                accommodationId
        );
    }


    /*@Transactional
    public void updateOverdueRents() {

        List<Rent> unpaidRents = rentRepository.findByStatus(RentStatus.UNPAID);
        LocalDate today = LocalDate.now();

        for (Rent rent : unpaidRents) {

            if (rent.getDueDate() != null && rent.getDueDate().isBefore(today)){

                rent.setStatus(RentStatus.OVERDUE);
                rentRepository.save(rent);
            }
        }
    }*/

    public void generateMonthlyRent(){
        List<User> tenants = userRepository.findByRole("Tenant");
        System.out.println("Tenants found: " + tenants.size());
        LocalDate today = LocalDate.now();
        for(User tenant:tenants){
            LocalDate joiningDate = tenant.getJoiningDate();
            System.out.println("Checking tenant: " + tenant.getName());
            if (joiningDate == null) {
                continue;
            }

           // int joiningDay = joiningDate.getDayOfMonth();
            int joiningDay = Math.min(joiningDate.getDayOfMonth(), today.lengthOfMonth());
            System.out.println("JoiningDay=" + joiningDay + " Today=" + today.getDayOfMonth());
            //int todayDay = today.getDayOfMonth();

            if (joiningDay == today.getDayOfMonth()) {

                LocalDate startOfMonth = today.withDayOfMonth(1);
                LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

                boolean rentExists =
                        rentRepository.existsByUserAndDueDateBetween(
                                tenant, startOfMonth, endOfMonth
                        );
                System.out.println("Rent exists for " + tenant.getName() + ": " + rentExists);


                if (!rentExists) {
                    Rent rent = new Rent();

                    rent.setInvoiceId("INV-" + System.currentTimeMillis());
                    rent.setAmount(tenant.getRoom().getRentAmount());
                    rent.setLateCharges(0.0);
                    rent.setElectricityCharges(0.0);
                    rent.setPaidAmount(0.0);
                    rent.setStatus(RentStatus.UNPAID);
                    rent.setDueDate(today.plusDays(0));
                    rent.setUser(tenant);

                    rentRepository.save(rent);

                    rentRepository.save(rent);
                    System.out.println("Rent generated for " + tenant.getName());
                }
            }
        }
    }

}
