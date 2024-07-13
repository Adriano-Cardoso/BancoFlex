package com.bankflex.accountservice.application.usecase;

import com.bankflex.accountservice.domain.model.dto.inbound.LoginInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.LoginOutbound;

import java.util.List;

public interface AuthUseCase {

    LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound);
    String createToken(String username, List<String> profiles);
    String validateToken(String token);


}
