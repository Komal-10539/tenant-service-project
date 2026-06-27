package com.example.tenant_service.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.tenant_service.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/tenant-service/api/v1/tenant/rent/proof")) {
            filterChain.doFilter(request, response);
            return;
        }

        String path = request.getRequestURI();

        if (path.contains("/tenant-service/api/v1/tenant/rent/proof")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (path.contains("/tenant/document")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (path.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        String username = null;
        try {
            username = jwtUtil.extractUsername(token);

            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null &&
                    jwtUtil.validateToken(token)) {
                String role = jwtUtil.extractRole(token);

                List<SimpleGrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("ROLE_" + role));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

}


