package com.bankflex.accountservice.application.mapper;

import com.bankflex.accountservice.domain.model.dto.inbound.LoginInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.LoginOutbound;
import com.bankflex.accountservice.model.LoginRequest;
import com.bankflex.accountservice.model.LoginResponse;
import com.bankflex.accountservice.utils.Constants;
import org.mapstruct.Mapping;

public interface AuthMapper {

    @Mapping(target = "token", source = "token")
    @Mapping( target = "type", constant = Constants.BEARER)
    LoginOutbound mapToDto(String token);

    LoginInbound toLoginRequestToInbound(LoginRequest loginRequest);

    LoginResponse toLoginInboundToResponse(LoginOutbound loginOutbound);
}
