package com.orangetalents.transacao.model.dto;

import com.orangetalents.transacao.model.Cartao;

public class CartaoMessage {
    private String id;
    private String email;

    public CartaoMessage(String id, String email) {
        this.id = id;
        this.email = email;
    }

    /**
     * Kafka Only
     */
    @Deprecated
    public CartaoMessage() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel(){
        return new Cartao(id,email);
    }
}
