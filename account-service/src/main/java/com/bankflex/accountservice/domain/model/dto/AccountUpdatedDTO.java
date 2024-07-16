package com.bankflex.accountservice.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdatedDTO {

    private String accountId;
    private String userId;
    private Map<String, Object> updatedFields;
    private LocalDateTime updatedAt;
    private String status;
}
