package com.example.tenant_service.service;

import com.example.tenant_service.dto.RecentActivitiesDto;
import com.example.tenant_service.entity.RecentActivity;
import com.example.tenant_service.repository.RecentActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentActivityService {

    private final RecentActivityRepository repository;

    public void logActivity(String message, String type, String username, Long accommodationId) {

        RecentActivity activity = new RecentActivity();
        activity.setMessage(message);
        activity.setType(type);
        activity.setUsername(username);
        activity.setAccommodationId(accommodationId);
        activity.setCreatedAt(LocalDateTime.now());

        repository.save(activity);
    }

    public List<RecentActivitiesDto> getRecentActivities(Long accommodationId) {

        return repository
                .findTop10ByAccommodationIdOrderByCreatedAtDesc(accommodationId)
                .stream()
                .map(a -> new RecentActivitiesDto(
                        a.getMessage(),
                        a.getType(),
                        getTimeAgo(a.getCreatedAt())
                ))
                .toList();
    }

    private String getTimeAgo(LocalDateTime time) {
        long minutes = ChronoUnit.MINUTES.between(time, LocalDateTime.now());

        if (minutes < 60) return minutes + " min ago";

        long hours = minutes / 60;
        if (hours < 24) return hours + " hrs ago";

        long days = hours / 24;
        return days + " d ago";
    }
}
