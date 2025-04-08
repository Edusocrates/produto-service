package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.AtualizarPrecoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.PrecoAtualizadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrecoAtualizadoListener {

    private final ObjectMapper objectMapper;
    private final AtualizarPrecoProdutoUseCase atualizarPrecoProdutoUseCase;

    @RabbitListener(queues = "produto.preco-atualizado.queue")
    public void receberMensagem(String mensagemJson) {
        try {
            PrecoAtualizadoEvent event = objectMapper.readValue(mensagemJson, PrecoAtualizadoEvent.class);
            log.info("Evento PrecoAtualizado recebido: {}", event);

            atualizarPrecoProdutoUseCase.atualizarPreco(event.produtoId(), event.novoPreco());

        } catch (Exception e) {
            log.error("Erro ao processar evento PrecoAtualizado", e);
        }
    }
}
