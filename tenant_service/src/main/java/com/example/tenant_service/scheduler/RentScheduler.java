package com.example.tenant_service.scheduler;

import com.example.tenant_service.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentScheduler {

    private final RentService rentService;

    @Scheduled(cron = "0 * * * * ?")
    public void generateRentAutomatically() {
        rentService.generateMonthlyRent();
    }

    /*@Scheduled(cron = "0 * * * * ?")
    public void checkOverdueRents() {
        rentService.updateOverdueRents();
    }*/
}