package com.orangetalents.transacao.listener;

import com.orangetalents.transacao.infrastructure.CartaoRepository;
import com.orangetalents.transacao.infrastructure.EstabelecimentoRepository;
import com.orangetalents.transacao.infrastructure.TransacaoRepository;
import com.orangetalents.transacao.model.Transacao;
import com.orangetalents.transacao.model.dto.TransacaoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class TransacaoConsumer {

    private final CartaoRepository cartaoRepository;
    private final TransacaoRepository transacaoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final Logger LOG = LoggerFactory.getLogger(TransacaoConsumer.class);

    private Transacao actualEvent;

    public TransacaoConsumer(CartaoRepository cartaoRepository,
                             TransacaoRepository transacaoRepository,
                             EstabelecimentoRepository estabelecimentoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoMessage transacaoMessage) {
        Transacao transacao = transacaoMessage.toModel();
        cartaoRepository.save(transacao.getCartao());
        estabelecimentoRepository.save(transacao.getEstabelecimento());
        transacaoRepository.save(transacao);

        actualEvent = transacao;

        LOG.info("Transação {} realizada com sucesso", transacao.getId());
    }

    public Transacao getActualEvent() {
        return actualEvent;
    }
}
