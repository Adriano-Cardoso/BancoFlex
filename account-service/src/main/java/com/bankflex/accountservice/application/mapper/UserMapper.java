package com.bankflex.accountservice.application.mapper;

import com.bankflex.accountservice.domain.model.User;
import com.bankflex.accountservice.domain.model.dto.inbound.UpdateUserInbound;
import com.bankflex.accountservice.domain.model.dto.inbound.UserInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.UserOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userInboundToUser(UserInbound userInbound);

    UserOutbound userToUserOutbound(User user);

    User covertUpdateUserInboundToUser(Long userId, UpdateUserInbound updateUserInbound);
}
