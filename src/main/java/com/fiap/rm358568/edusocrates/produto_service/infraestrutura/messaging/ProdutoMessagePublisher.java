package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig.*;

@Component
@RequiredArgsConstructor
public class ProdutoMessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public void enviarMensagem(Object payload) {
        rabbitTemplate.convertAndSend(PRODUTO_CRIADO_EXCHANGE, PRODUTO_CRIADO_ROUTING_KEY, payload);
    }
}
