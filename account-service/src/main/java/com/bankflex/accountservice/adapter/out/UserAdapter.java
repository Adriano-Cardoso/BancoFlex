package com.bankflex.accountservice.adapter.out;

import com.bankflex.accountservice.adapter.out.repository.UserRepository;
import com.bankflex.accountservice.domain.model.dto.inbound.UserInbound;
import com.bankflex.accountservice.domain.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserInbound userInbound) {

    }
}
