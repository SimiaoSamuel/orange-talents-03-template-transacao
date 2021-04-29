package com.orangetalents.transacao.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.transacao.interface_rest.client.TransacaoClientRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransacaoRequest {
    @NotBlank
    @Email
    @JsonProperty
    private String email;

    @JsonCreator
    public TransacaoRequest(@JsonProperty("email") @NotBlank @Email String email) {
        this.email = email;
    }

    public TransacaoClientRequest toClientRequest(String idCartao){
        return new TransacaoClientRequest(idCartao,email);
    }
}
