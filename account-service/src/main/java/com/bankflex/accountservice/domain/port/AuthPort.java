package com.bankflex.accountservice.domain.port;

import com.bankflex.accountservice.domain.model.dto.inbound.LoginInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.LoginOutbound;

import java.util.List;

public interface AuthPort {

    String createToken(String username, List<String> profiles);
    String validateToken(String token);
    LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound);

}
