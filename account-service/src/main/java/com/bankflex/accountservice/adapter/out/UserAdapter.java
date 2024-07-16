package com.bankflex.accountservice.adapter.out;

import com.bankflex.accountservice.adapter.out.repository.RoleRepository;
import com.bankflex.accountservice.adapter.out.repository.UserRepository;
import com.bankflex.accountservice.application.mapper.UserMapper;
import com.bankflex.accountservice.domain.model.Role;
import com.bankflex.accountservice.domain.model.User;
import com.bankflex.accountservice.domain.model.dto.inbound.UpdateUserInbound;
import com.bankflex.accountservice.domain.model.dto.inbound.UserInbound;
import com.bankflex.accountservice.domain.model.dto.outbound.UserOutbound;
import com.bankflex.accountservice.domain.port.UserPort;
import com.bankflex.accountservice.utils.Constants;
import com.bankflex.accountservice.utils.Message;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserOutbound createUser(UserInbound userInbound) {
        log.info("Salvando usuário - Nome de usuário: {}, Email: {}", userInbound.getUsername(),
                userInbound.getEmail());

        User user = userMapper.userInboundToUser(userInbound);

        userRepository.findByEmail(user.getEmail()).ifPresent(p -> {
            throw Message.IS_PRESENT_USER.asBusinessException();
        });

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRoles(Collections.singletonList(fetchRole()));

        userRepository.save(user);

        return userMapper.userToUserOutbound(user);

    }

    @Transactional
    @Override
    public UserOutbound update(Long userId, UpdateUserInbound updateUserInbound) {
        User user = userMapper.covertUpdateUserInboundToUser(userId, updateUserInbound);

        fetchUserById(userId);

        user.update(updateUserInbound);

        log.info("Atualizando usuário - ID: {}, Nome: {}", user.getUserId(), user.getName());
        return userMapper.userToUserOutbound(user);
    }

    @Override
    public void delete(Long userId) {
        User user = fetchUserById(userId);
        userRepository.delete(user);
        log.info("Usuário deletado com sucesso - ID: {}", userId);
    }

    @Override
    public Page<UserOutbound> searchRegisteredUsers(Pageable pageable, String identifier) {
        log.info("Buscando usuários registrados - Identificador: {}", identifier);
        return userRepository.searchRegisteredUsers(pageable, identifier);
    }

    private Role fetchRole() {
        log.debug("Buscando perfil: {}", Constants.ANALYST);
        return roleRepository.findByName(Constants.ANALYST)
                .orElseThrow(Message.NAME_PROFILE_NOT_FOUND::asBusinessException);
    }

    public User fetchUserById(Long userId) {
        log.info("Buscando usuário por ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(Message.USER_NOT_FOUND::asBusinessException);
    }
}
