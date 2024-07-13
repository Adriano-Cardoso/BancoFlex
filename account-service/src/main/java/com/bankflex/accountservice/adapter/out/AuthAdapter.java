package com.bankflex.accountservice.adapter.out;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bankflex.accountservice.application.mapper.AuthMapper;
import com.bankflex.accountservice.domain.model.Role;
import com.bankflex.accountservice.domain.model.User;
import com.bankflex.accountservice.domain.model.dto.inbound.LoginInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.LoginOutbound;
import com.bankflex.accountservice.domain.port.AuthPort;
import com.bankflex.accountservice.utils.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

import static com.bankflex.accountservice.utils.Constants.ISSUER;
import static com.bankflex.accountservice.utils.Constants.PROFILES_CLAIM;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthAdapter implements AuthPort {

    @Value("${api.jwt.secret}")
    private String secretKey;

    @Value("${api.jwt.expiration}")
    private String expiration;

    private final AuthenticationManager authenticationManager;
    private final AuthMapper tokenMapper;

    @Override
    public String createToken(String username, List<String> profiles) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(username)
                    .withClaim(PROFILES_CLAIM, profiles)
                    .withExpiresAt(Instant.parse(expiration)    )
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("Failed to create token for user: {}", username, exception);
            throw Message.TOKEN_ERROR.asBusinessException();
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("l2m-travelex-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.error("Failed to validate token: {}", token, exception);
            throw Message.TOKEN_ERROR.asBusinessException();
        }
    }

    @Override
    public LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInbound.getEmail(), loginInbound.getPassword()));

            User user = (User) auth.getPrincipal();
            String token = createToken(user.getEmail(), user.getRoles().stream().map(Role::getName).toList());

            return tokenMapper.mapToDto(token);
        } catch (BadCredentialsException e) {
            log.error("Failed to authenticate user: {}", loginInbound.getEmail(), e);
            throw Message.INVALID_CREDENTIALS.asBusinessException();
        }
    }
}
