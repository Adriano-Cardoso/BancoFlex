package com.bankflex.accountservice.domain.port;

import com.bankflex.accountservice.domain.model.dto.inbound.UpdateUserInbound;
import com.bankflex.accountservice.domain.model.dto.inbound.UserInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.UserOutbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserPort {

    UserOutbound createUser(UserInbound userInbound);
    UserOutbound update(Long userId, UpdateUserInbound updateUserInbound);
    Page<UserOutbound> searchRegisteredUsers(Pageable pageable, String identifier);
    void delete(Long userId);
}
