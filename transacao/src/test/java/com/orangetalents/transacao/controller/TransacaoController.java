package com.orangetalents.transacao.controller;

import com.orangetalents.transacao.TransacaoApplication;
import com.orangetalents.transacao.databuilder.Cartao;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TransacaoApplication.class)
@AutoConfigureMockMvc
public class TransacaoController {

    @Autowired
    private MockMvc mockMvc;

    CountDownLatch count = new CountDownLatch(1);

    @Test
    public void testando() throws Exception {
        String json =
                        "{\n" +
                        "    \"email\": \"luram.archanjo@zup.com.br\"\n" +
                        "}";
        mockMvc
                .perform(post("/cartoes/5541da9c-79c5-44fb-b701-cc50ed07b45d/transacoes")
                        .contentType(MediaType.APPLICATION_JSON).content(json));
        count.await(30000, TimeUnit.MILLISECONDS);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/cartoes/5541da9c-79c5-44fb-b701-cc50ed07b45d/transacoes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void seCartaoNaoExisteRetornaNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/cartoes/cartaonaoexistente/transacoes"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
