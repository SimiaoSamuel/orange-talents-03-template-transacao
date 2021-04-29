package com.orangetalents.transacao.model;

import com.orangetalents.transacao.model.Cartao;
import com.orangetalents.transacao.model.Estabelicimento;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @NotBlank
    @Column(nullable = false)
    private String id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estabelicimento estabelecimento;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cartao cartao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime efetivadaEm;

    public Transacao(@NotBlank String id, @NotNull BigDecimal valor,
                     @NotNull Estabelicimento estabelecimento, @NotNull Cartao cartao,
                     @NotNull LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public Transacao() {
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelicimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    @Override
    public String toString() {
        return "EventoDeTransacao{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelicimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetidaEm=" + efetivadaEm +
                '}';
    }
}
