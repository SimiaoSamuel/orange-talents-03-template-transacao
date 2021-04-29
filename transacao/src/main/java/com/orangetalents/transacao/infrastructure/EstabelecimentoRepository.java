package com.orangetalents.transacao.infrastructure;

import com.orangetalents.transacao.model.Estabelicimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelicimento,Long> {
}
