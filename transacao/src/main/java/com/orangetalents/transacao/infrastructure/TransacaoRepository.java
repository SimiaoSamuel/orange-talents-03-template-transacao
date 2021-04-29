package com.orangetalents.transacao.infrastructure;

import com.orangetalents.transacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao,String> {
    List<Transacao> findTop10ByCartaoIdOrderByEfetivadaEmDesc(String id);
}
