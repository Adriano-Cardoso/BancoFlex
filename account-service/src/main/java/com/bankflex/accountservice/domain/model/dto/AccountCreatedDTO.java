package com.bankflex.accountservice.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedDTO {
    private String accountId;
    private String userId;
    private String accountType;
    private double balance;
    private LocalDateTime createdAt;
    private String status;
}
