package com.example.tenant_service.service;

import com.example.tenant_service.dto.RecentComplaintDto;
import com.example.tenant_service.dto.StatusUpdateResponse;
import com.example.tenant_service.entity.Complaint;
import com.example.tenant_service.entity.ComplaintDocument;
import com.example.tenant_service.entity.Room;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.ComplaintStatus;
import com.example.tenant_service.repository.ComplaintDocumentRepository;
import com.example.tenant_service.repository.ComplaintRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerComplaintService {
    private final ComplaintRepository complaintRepository;
    private final ComplaintDocumentRepository complaintDocumentRepository;
    private final RecentActivityService recentActivityService;
    private final UserRepository userRepository;

    public Page<RecentComplaintDto> getAllComplaints(String accommodationId,
                                                     ComplaintStatus status,
                                                     int page) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());

        Page<Complaint> complaints;

        if (status != null) {
            complaints = complaintRepository
                    .findByAccommodationIdAndStatus(
                            accommodationId,
                            status,
                            pageable
                    );
        } else {
            complaints = complaintRepository
                    .findByAccommodationId(
                            accommodationId,
                            pageable
                    );
        }

        return complaints.map(this::convertToDto);
    }

    private RecentComplaintDto convertToDto(Complaint complaint) {

        RecentComplaintDto dto = new RecentComplaintDto();

        dto.setComplaintNumber(complaint.getComplaintNumber());
        dto.setRoomId(complaint.getRoomId());
        dto.setComplainType(complaint.getSubject());
        dto.setHasAttachment(complaint.isHasAttachment());
        dto.setDescription(complaint.getDescription());
        dto.setAccommodationId(complaint.getAccommodationId());
        dto.setStatus(complaint.getStatus().name());
        dto.setCreatedAt(complaint.getCreatedAt());

        return dto;
    }

    public List<String> getAttachments(String complaintNumber) {
        if (complaintNumber == null || complaintNumber.isBlank()) {
            return List.of();
        }

        Optional<Complaint> complaintOpt =
                complaintRepository.findByComplaintNumber(complaintNumber);

        if (complaintOpt.isEmpty()) {
            return List.of();
        }

        Complaint complaint = complaintOpt.get();



        if (!complaint.isHasAttachment()) {
            return List.of();
        }

        List<ComplaintDocument> documents =
                complaintDocumentRepository.findByComplaintId(complaint.getId());


        return documents.stream()
                .map(doc -> "http://localhost:8080/tenant-service/api/tenant/complaint/document/" + doc.getId())
                .toList();
    }

    public void updateStatus(String complaintNumber,String accommodationId,ComplaintStatus newStatus) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<User> users = userRepository.findByUserName(username);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = users.get(0);
        Room room = user.getRoom();

        if (room == null) {
            throw new RuntimeException("Room not assigned");
        }

        Complaint complaint = complaintRepository
                .findByComplaintNumber(complaintNumber)
                        .orElseThrow(() -> new RuntimeException("Complaint not found"));

        ComplaintStatus currentStatus = complaint.getStatus();

        if (currentStatus == ComplaintStatus.RAISED) {
            if (newStatus != ComplaintStatus.IN_PROGRESS &&
                    newStatus != ComplaintStatus.IN_REVIEW &&
                    newStatus != ComplaintStatus.RESOLVED &&
                    newStatus != ComplaintStatus.CLOSED)
                throw new RuntimeException("Invalid status transition");
        }
        if (currentStatus == ComplaintStatus.IN_PROGRESS) {
            if (newStatus != ComplaintStatus.IN_REVIEW &&
                    newStatus != ComplaintStatus.RESOLVED &&
                    newStatus != ComplaintStatus.CLOSED) {
                throw new RuntimeException("Invalid status transition");
            }
        }

        if (currentStatus == ComplaintStatus.IN_REVIEW) {
            if (newStatus != ComplaintStatus.RESOLVED &&
                    newStatus != ComplaintStatus.CLOSED) {
                throw new RuntimeException("Invalid status transition");
            }
        }

        if (currentStatus == ComplaintStatus.RESOLVED) {
            if (newStatus != ComplaintStatus.CLOSED) {
                throw new RuntimeException("Invalid status transition");
            }
        }

        if (currentStatus == ComplaintStatus.CLOSED) {
            throw new RuntimeException("Cannot update closed complaint");
        }

        complaint.setStatus(newStatus);
        complaintRepository.save(complaint);
        recentActivityService.logActivity(
                "Complaint resolved for Room " + room.getRoomNumber(),
                "COMPLAINT_RESOLVED",
                username,
                Long.valueOf(accommodationId)
        );

    }


}
