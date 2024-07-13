package com.bankflex.accountservice.domain.model.dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInbound {

    private String name;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    private String role;
}
