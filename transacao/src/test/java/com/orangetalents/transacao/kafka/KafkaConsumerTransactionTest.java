package com.orangetalents.transacao.kafka;

import com.orangetalents.transacao.listener.TransacaoConsumer;
import com.orangetalents.transacao.model.Transacao;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class KafkaConsumerTransactionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransacaoConsumer consumer;

    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void givenKafkaDockerContainer_whenMessageReceived()
            throws Exception {
        String json =
                "{\n" +
                        "    \"email\": \"luram.archanjo@zup.com.br\"\n" +
                        "}";
        mockMvc
                .perform(post("/cartoes/5541da9c-79c5-44fb-b701-cc50ed07b45d/transacoes")
                        .contentType(MediaType.APPLICATION_JSON).content(json));

        latch.await(30000, TimeUnit.MILLISECONDS);
        Transacao actualEvent = consumer.getActualEvent();

        Assertions.assertEquals("5541da9c-79c5-44fb-b701-cc50ed07b45d",
                actualEvent.getCartao().getId());
    }

}
