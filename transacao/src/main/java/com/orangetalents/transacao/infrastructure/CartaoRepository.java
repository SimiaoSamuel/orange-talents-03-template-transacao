package com.orangetalents.transacao.infrastructure;

import com.orangetalents.transacao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao,Long> {
}
