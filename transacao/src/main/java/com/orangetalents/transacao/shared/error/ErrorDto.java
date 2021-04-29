package com.orangetalents.transacao.shared.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    private String reason;

    private HttpStatus error;

    public ErrorDto(String field, String reason, HttpStatus error) {
        this.field = field;
        this.reason = reason;
        this.error = error;
    }

    public ErrorDto(ErroApiException exception) {
        this.reason = exception.getReason();
        this.error = exception.getErrorStatus();
    }

    public String getField() {
        return field;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getError() {
        return error;
    }
}
