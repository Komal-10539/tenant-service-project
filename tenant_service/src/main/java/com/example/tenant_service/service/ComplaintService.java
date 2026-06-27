package com.example.tenant_service.service;

import com.example.tenant_service.dto.ComplaintPageResponse;
import com.example.tenant_service.dto.RaiseComplaintRequest;
import com.example.tenant_service.dto.RecentComplaintDto;
import com.example.tenant_service.entity.Complaint;
import com.example.tenant_service.entity.ComplaintDocument;
import com.example.tenant_service.entity.Room;
import com.example.tenant_service.entity.User;
import com.example.tenant_service.enums.ComplaintStatus;
import com.example.tenant_service.repository.ComplaintDocumentRepository;
import com.example.tenant_service.repository.ComplaintRepository;
import com.example.tenant_service.repository.RecentActivityRepository;
import com.example.tenant_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintDocumentRepository complaintDocumentRepository;
    private final RecentActivityService recentActivityService;
    private final UserRepository userRepository;

    public Page<RecentComplaintDto> getComplaints(String accommodationId , int page){

        Pageable pageable =
                PageRequest.of(page,10, Sort.by("createdAt").descending());

        Page<Complaint> complaints =
                complaintRepository.findAll(pageable);

        return complaints.map(this::convertToDto);
    }

    public RecentComplaintDto raiseComplaint(RaiseComplaintRequest request) throws IOException {
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

        Complaint complaint = new Complaint();

        complaint.setComplaintNumber("CLT-" + System.currentTimeMillis());
        complaint.setSubject(request.getSubject());
        complaint.setDescription(request.getDescription());
        complaint.setAccommodationId(request.getAccommodationId());
        complaint.setRoomId("101");
        complaint.setStatus(ComplaintStatus.RAISED);
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setHasAttachment(false);
        Complaint savedComplaint = complaintRepository.save(complaint);
        //complaintRepository.save(complaint);

        if(request.getImages() != null && !request.getImages().isEmpty()){

            ComplaintDocument document = new ComplaintDocument();

            document.setFileName(request.getImages().getOriginalFilename());
            document.setFileType(request.getImages().getContentType());
            document.setFileData(request.getImages().getBytes());

            document.setComplaint(savedComplaint);
            complaintDocumentRepository.save(document);
        }
        savedComplaint.setHasAttachment(true);
        complaintRepository.save(savedComplaint);

        recentActivityService.logActivity(
                "New complaint raised for " + complaint.getIssueType() + " in Room " + room.getRoomNumber(),
                "COMPLAINT_RAISED",
                username,
                user.getHostel().getId()
        );

        return convertToDto(savedComplaint);
    }

    public List<String> getAttachments(String complaintNumber) {

        Complaint complaint = complaintRepository
                        .findByComplaintNumber(complaintNumber)
                        .orElseThrow();

        if (!complaint.isHasAttachment()) {
            return List.of();
        }

        List<ComplaintDocument> documents =
                complaintDocumentRepository.findByComplaintId(complaint.getId());


        return documents.stream()
                .map(doc -> "http://localhost:8080/tenant-service/api/tenant/complaint/document/" + doc.getId())
                .toList();
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
}