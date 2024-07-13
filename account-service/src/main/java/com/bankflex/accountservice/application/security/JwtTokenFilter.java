package com.bankflex.accountservice.application.security;

import com.bankflex.accountservice.adapter.out.repository.UserRepository;
import com.bankflex.accountservice.domain.model.User;
import com.bankflex.accountservice.domain.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthService authService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if (token != null) {
            log.info("Token found: {}", token);

            var login = authService.validateToken(token);
            log.info("Login from token: {}", login);

            Optional<User> user = userRepository.findByEmail(login);
            if (user.isPresent()) {
                log.info("User found: {}", user.get().getUsername());

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("Authentication set in SecurityContextHolder");
            } else {
                log.warn("User not found for login: {}", login);
            }
        } else {
            log.info("No token found in the request");
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            log.info("Authorization header not found in the request");
            return null;
        }
        var token = authHeader.replace("Bearer ", "");
        log.info("Recovered token: {}", token);
        return token;
    }
}

