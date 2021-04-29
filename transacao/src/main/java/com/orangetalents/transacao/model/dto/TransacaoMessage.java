package com.orangetalents.transacao.model.dto;

import com.orangetalents.transacao.model.Cartao;
import com.orangetalents.transacao.model.Estabelicimento;
import com.orangetalents.transacao.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoMessage {
    private String id;

    private BigDecimal valor;

    private EstabelecimentoMessage estabelecimento;

    private CartaoMessage cartao;

    private LocalDateTime efetivadaEm;

    public TransacaoMessage(String id, BigDecimal valor, EstabelecimentoMessage estabelecimento,
                            CartaoMessage cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    /**
     * Kafka Only
     */
    @Deprecated
    public TransacaoMessage() {
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoMessage getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoMessage getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel(){
        return new Transacao(id,valor,estabelecimento.toModel(), cartao.toModel(), efetivadaEm);
    }
}
