package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private static final String EXCHANGE = "produto.exchange";
    private static final String ROUTING_KEY = "produto.criado";

    public void enviarProdutoCriado(ProdutoCriadoEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, json);
            log.info("Evento ProdutoCriado enviado: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar ProdutoCriadoEvent", e);
        }
    }
}
