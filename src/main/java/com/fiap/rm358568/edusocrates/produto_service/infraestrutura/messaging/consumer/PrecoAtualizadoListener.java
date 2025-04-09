package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.AtualizarPrecoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.PrecoAtualizadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PrecoAtualizadoListener {

    private final AtualizarPrecoProdutoUseCase atualizarPrecoUseCase;

    @RabbitListener(queues = RabbitMQConfig.PRECO_ATUALIZADO_QUEUE)
    public void ouvirPrecoAtualizado(PrecoAtualizadoEvent event) {
        log.info("Recebido evento de atualização de preço: {}", event);
        if (event == null) {
            return;
        }
        atualizarPrecoUseCase.atualizarPreco(event.produtoId(), event.novoPreco());
    }
}
