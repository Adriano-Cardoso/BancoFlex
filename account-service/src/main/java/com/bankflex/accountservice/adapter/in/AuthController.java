package com.bankflex.accountservice.adapter.in;

import com.bankflex.accountservice.api.AuthApi;
import com.bankflex.accountservice.application.mapper.AuthMapper;
import com.bankflex.accountservice.domain.model.dto.outbound.LoginOutbound;
import com.bankflex.accountservice.domain.service.AuthService;
import com.bankflex.accountservice.model.LoginRequest;
import com.bankflex.accountservice.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @Override
    public ResponseEntity<LoginResponse> authenticateAndGenerateToken(LoginRequest loginRequest) {
        LoginOutbound loginOutbound = authService.authenticateAndGenerateToken(authMapper.toLoginRequestToInbound(loginRequest));
        return ResponseEntity.status(HttpStatus.OK).body(authMapper.toLoginInboundToResponse(loginOutbound));
    }
}
