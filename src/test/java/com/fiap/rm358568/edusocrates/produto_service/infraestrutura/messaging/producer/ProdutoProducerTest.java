package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.producer;

import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ProdutoProducer produtoProducer;

    @Test
    void enviarProdutoCriado_deveEnviarMensagemComEventoValido() {
        ProdutoCriadoEvent event = new ProdutoCriadoEvent(UUID.randomUUID(), "nomeProduto", BigDecimal.valueOf(100.0), 10);

        produtoProducer.enviarProdutoCriado(event);

        verify(rabbitTemplate, times(1)).convertAndSend(
                RabbitMQConfig.PRODUTO_CRIADO_EXCHANGE,
                RabbitMQConfig.PRODUTO_CRIADO_ROUTING_KEY,
                event
        );
    }

    @Test
    void enviarProdutoSincronizacao_deveEnviarMensagemComPayloadValido() {
        Object payload = new Object();

        produtoProducer.enviarProdutoSincronizacao(payload);

        verify(rabbitTemplate, times(1)).convertAndSend(
                RabbitMQConfig.PEDIDO_PRODUTO_SYNC_EXCHANGE,
                RabbitMQConfig.PEDIDO_PRODUTO_SYNC_ROUTING_KEY,
                payload
        );
    }
}