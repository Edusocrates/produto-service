package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig.PRODUTO_CRIADO_EXCHANGE;
import static com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig.PRODUTO_CRIADO_ROUTING_KEY;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoMessagePublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ProdutoMessagePublisher produtoMessagePublisher;

    @Test
    void enviarMensagem_deveEnviarMensagemComPayloadValido() {
        Object payload = new Object();

        produtoMessagePublisher.enviarMensagem(payload);

        verify(rabbitTemplate, times(1)).convertAndSend(PRODUTO_CRIADO_EXCHANGE, PRODUTO_CRIADO_ROUTING_KEY, payload);
    }
}