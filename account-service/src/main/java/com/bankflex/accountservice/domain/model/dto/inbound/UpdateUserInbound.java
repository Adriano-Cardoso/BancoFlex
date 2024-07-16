package com.bankflex.accountservice.domain.model.dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInbound {

    private String name;
    private String cpfOrCnpj;
    private String email;
}
