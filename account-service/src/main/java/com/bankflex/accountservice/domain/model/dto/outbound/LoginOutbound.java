package com.bankflex.accountservice.domain.model.dto.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutbound {

    private String token;

    private String type;
}
