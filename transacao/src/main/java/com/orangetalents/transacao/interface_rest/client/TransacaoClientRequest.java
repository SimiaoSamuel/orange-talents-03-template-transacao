package com.orangetalents.transacao.interface_rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransacaoClientRequest {
    @NotBlank
    @JsonProperty
    private String id;

    @NotBlank
    @Email
    @JsonProperty
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TransacaoClientRequest(@NotBlank String id, @NotBlank @Email String email) {
        this.id = id;
        this.email = email;
    }
}
