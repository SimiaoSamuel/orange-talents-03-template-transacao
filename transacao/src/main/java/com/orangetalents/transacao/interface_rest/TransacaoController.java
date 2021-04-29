package com.orangetalents.transacao.interface_rest;

import com.orangetalents.transacao.infrastructure.TransacaoRepository;
import com.orangetalents.transacao.interface_rest.client.TransacaoClient;
import com.orangetalents.transacao.interface_rest.client.TransacaoClientRequest;
import com.orangetalents.transacao.model.Transacao;
import com.orangetalents.transacao.model.dto.TransacaoRequest;
import com.orangetalents.transacao.shared.error.ErroApiException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cartoes/{id}/transacoes")
public class TransacaoController {

    private final TransacaoRepository repository;
    private final TransacaoClient client;

    public TransacaoController(TransacaoRepository repository, TransacaoClient client) {
        this.repository = repository;
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<Void> startTransaction(@PathVariable String id,@RequestBody @Valid TransacaoRequest transacaoRequest){
        TransacaoClientRequest request = transacaoRequest.toClientRequest(id);
        try {
            client.runTransaction(request);
        } catch (FeignException e){
            throw new ErroApiException("Erro ao chamar outro serviço", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> stopTransaction(@PathVariable String id){
        try {
            client.stopTransaction(id);
        } catch (FeignException e){
            throw new ErroApiException("Erro ao chamar outro serviço", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Transacao> getLastTenTransaction(@PathVariable String id){
        List<Transacao> transacoes = repository.findTop10ByCartaoIdOrderByEfetivadaEmDesc(id);

        if(transacoes.isEmpty())
            throw new ErroApiException("Nenhuma transação feita nesse cartão ou cartão não existe",
                    HttpStatus.NOT_FOUND);

        return transacoes;
    }
}
