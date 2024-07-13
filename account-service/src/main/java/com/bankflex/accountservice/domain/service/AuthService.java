package com.bankflex.accountservice.domain.service;

import com.bankflex.accountservice.application.usecase.AuthUseCase;
import com.bankflex.accountservice.domain.model.dto.inbound.LoginInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.LoginOutbound;
import com.bankflex.accountservice.domain.port.AuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final AuthPort authPort;

    @Override
    public LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound) {
        return authPort.authenticateAndGenerateToken(loginInbound);
    }

    @Override
    public String createToken(String username, List<String> profiles) {
        return authPort.createToken(username, profiles);
    }

    @Override
    public String validateToken(String token) {
        return authPort.validateToken(token);    }
}
