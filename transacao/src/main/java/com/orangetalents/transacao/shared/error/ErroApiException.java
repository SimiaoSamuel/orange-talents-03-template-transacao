package com.orangetalents.transacao.shared.error;

import org.springframework.http.HttpStatus;

public class ErroApiException extends RuntimeException{
    private String reason;
    private HttpStatus errorStatus;

    public String getReason() {
        return reason;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public ErroApiException(String reason, HttpStatus errorStatus) {
        this.reason = reason;
        this.errorStatus = errorStatus;
    }
}
