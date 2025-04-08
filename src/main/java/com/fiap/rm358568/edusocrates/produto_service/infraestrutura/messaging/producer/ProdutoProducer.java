package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.producer;


import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarProdutoCriado(ProdutoCriadoEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PRODUTO_CRIADO_EXCHANGE,
                RabbitMQConfig.PRODUTO_CRIADO_ROUTING_KEY,
                event
        );
    }

    public void enviarProdutoSincronizacao(Object payload) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PEDIDO_PRODUTO_SYNC_EXCHANGE,
                RabbitMQConfig.PEDIDO_PRODUTO_SYNC_ROUTING_KEY,
                payload
        );
    }
}