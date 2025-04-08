package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUTO_EXCHANGE = "produto.exchange";
    public static final String PRODUTO_QUEUE = "produto.queue";
    public static final String PRODUTO_ROUTING_KEY = "produto.routingkey";

    @Bean
    public DirectExchange produtoExchange() {
        return new DirectExchange(PRODUTO_EXCHANGE);
    }

    @Bean
    public Queue produtoQueue() {
        return new Queue(PRODUTO_QUEUE, true); // durable
    }

    @Bean
    public Binding produtoBinding(Queue produtoQueue, DirectExchange produtoExchange) {
        return BindingBuilder.bind(produtoQueue)
                .to(produtoExchange)
                .with(PRODUTO_ROUTING_KEY);
    }
}
