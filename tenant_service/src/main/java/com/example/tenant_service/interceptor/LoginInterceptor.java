package com.example.tenant_service.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.tenant_service.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.equals("/auth/login")) {
            return true;
        }

        String client = request.getHeader("Client-Id");

        if (client == null || client.isEmpty()) {
            log.warn("Blocked request  - Client-Id header missing");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Client-Id header missing");
            return false;
        }

        return true;
    }
}
