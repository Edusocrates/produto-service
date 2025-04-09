package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;


import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ProcessarPedidoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.PedidoProdutoSyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PedidoSyncListener {

    private final ProcessarPedidoProdutoUseCase useCase;

    public PedidoSyncListener(ProcessarPedidoProdutoUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "pedido-produto-sync-queue")
    public void listen(PedidoProdutoSyncEvent event) {
        log.info("Recebido evento de pedido: {}", event);
        if (event.getItens() == null || event.getItens().isEmpty()) {
            return;
        }
        Map<String, Integer> produtosQuantidade = new HashMap<>();

        event.getItens().forEach(item -> produtosQuantidade.put(item.getProdutoId(), item.getQuantidade()));
        log.info("Processando pedido com os produtos: {}", produtosQuantidade);

        useCase.executar(produtosQuantidade);
        log.info("Pedido processado com sucesso!");
    }
}
