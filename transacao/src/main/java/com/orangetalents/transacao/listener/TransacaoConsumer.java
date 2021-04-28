package com.orangetalents.transacao.listener;

import com.orangetalents.transacao.model.EventoDeTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.concurrent.CountDownLatch;

@Component
public class TransacaoConsumer {

    private final EntityManager em;
    private final Logger LOG = LoggerFactory.getLogger(TransacaoConsumer.class);

    private EventoDeTransacao actualEvent;

    public TransacaoConsumer(EntityManager em) {
        this.em = em;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void ouvir(@Valid EventoDeTransacao eventoDeTransacao) {
        em.persist(eventoDeTransacao.getCartao());
        em.persist(eventoDeTransacao.getEstabelecimento());
        em.persist(eventoDeTransacao);

        actualEvent = eventoDeTransacao;

        LOG.info("Transação {} realizada com sucesso", eventoDeTransacao.getId());
    }

    public EventoDeTransacao getActualEvent() {
        return actualEvent;
    }
}
