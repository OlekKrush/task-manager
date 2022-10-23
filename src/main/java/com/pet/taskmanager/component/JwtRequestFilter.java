package com.pet.taskmanager.component;

import com.pet.taskmanager.entity.UserProfile;
import com.pet.taskmanager.service.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtRequestFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        if (request.getRequestURI().contains("/auth/default") ||
                request.getRequestURI().contains("/auth/registration") ||
                request.getRequestURI().contains("/auth/check)")) {
            chain.doFilter(request, response);

        }
        else {
            if (header == null ||
                    header.trim().isEmpty() ||
                    !header.startsWith("Bearer ")) {

                chain.doFilter(request, response);
                return;
            }

            // Get jwt token and validate
            final String token = header.split(" ")[1].trim();
            if (!jwtService.validateToken(token)) {
                chain.doFilter(request, response);
                return;
            }

            // Get user identity and set it on the spring security context
            UserProfile userDetails = jwtService.getUserDetails(token);

            UsernamePasswordAuthenticationToken
                    authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }

    }
}
