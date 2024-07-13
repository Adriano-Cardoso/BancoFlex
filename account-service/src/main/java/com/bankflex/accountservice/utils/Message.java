package com.bankflex.accountservice.utils;

import com.bankflex.accountservice.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    TOKEN_ERROR("Token invalido", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS("Credenciais de autenticação inválidas.", HttpStatus.UNAUTHORIZED),
    INVALID_RESET_TOKEN("Token de reset de senha está inválido", HttpStatus.BAD_REQUEST);

    private String value;
    private String description;
    private HttpStatus statusCode;

    Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
