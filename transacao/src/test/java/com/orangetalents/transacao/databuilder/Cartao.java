package com.orangetalents.transacao.databuilder;

public class Cartao {
    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao id(String id){
        this.id = id;
        return this;
    }

    public Cartao email(String email){
        this.email = email;
        return this;
    }
}
