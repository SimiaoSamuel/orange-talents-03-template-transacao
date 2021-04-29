package com.orangetalents.transacao.model.dto;

import com.orangetalents.transacao.model.Estabelicimento;

public class EstabelecimentoMessage {
    private Long idEstabelecimento;

    private String nome;

    private String cidade;

    private String endereco;

    public EstabelecimentoMessage(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    /**
     * Kafka Only
     */
    @Deprecated
    public EstabelecimentoMessage() {
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public Estabelicimento toModel(){
        return new Estabelicimento(nome,cidade,endereco);
    }
}
