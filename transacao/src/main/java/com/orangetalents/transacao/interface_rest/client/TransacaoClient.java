package com.orangetalents.transacao.interface_rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "transaction", url = "http://localhost:7777/api/cartoes")
public interface TransacaoClient {

    @PostMapping
    void runTransaction(TransacaoClientRequest request);

    @DeleteMapping("/{idCartao}")
    void stopTransaction(@PathVariable String idCartao);
}
