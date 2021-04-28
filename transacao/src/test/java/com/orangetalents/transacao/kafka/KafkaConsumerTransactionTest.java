package com.orangetalents.transacao.kafka;

import com.orangetalents.transacao.listener.TransacaoConsumer;
import com.orangetalents.transacao.model.EventoDeTransacao;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class KafkaConsumerTransactionTest {

    @ClassRule
    public static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    @Autowired
    private TransacaoConsumer consumer;

    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void givenKafkaDockerContainer_whenMessageReceived()
            throws Exception {
        latch.await(1000 * 60 * 5, TimeUnit.MILLISECONDS);
        EventoDeTransacao actualEvent = consumer.getActualEvent();

        Assertions.assertEquals("5541da9c-79c5-44fb-b701-cc50ed07b45d",
                actualEvent.getCartao().getId());
    }

}
