package com.bankflex.accountservice.domain.port;

import com.bankflex.accountservice.domain.model.dto.inbound.UserInbound;

public interface UserPort {

    void createUser(UserInbound userInbound);
}
