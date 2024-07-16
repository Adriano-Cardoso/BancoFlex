package com.bankflex.accountservice.utils;

import com.bankflex.accountservice.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    TOKEN_ERROR("Token invalido", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS("Credenciais de autenticação inválidas.", HttpStatus.UNAUTHORIZED),
    INVALID_RESET_TOKEN("Token de reset de senha está inválido", HttpStatus.BAD_REQUEST),
    IS_PRESENT_USER("O usuário já existe", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND("O email não foi encontrado para o usuário", HttpStatus.NOT_FOUND),
    NAME_PROFILE_NOT_FOUND("O Perfil selecionado nâo existe", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("O Usuário não foi encontrado", HttpStatus.NOT_FOUND);

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
