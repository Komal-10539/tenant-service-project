package com.example.tenant_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.tenant_service.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Registering LoginInterceptor for request validation...");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("secure/**");
               // .addPathPatterns("/auth/login");
        log.info("LoginInterceptor registered successfully for paths: /** , /auth/login");
    }
}
