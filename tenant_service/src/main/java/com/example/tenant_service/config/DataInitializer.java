package com.example.tenant_service.config;

import com.example.tenant_service.service.DummyDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(DummyDataService dummyDataService) {
        return args -> {
            dummyDataService.insertDummyData();
        };
    }
}
