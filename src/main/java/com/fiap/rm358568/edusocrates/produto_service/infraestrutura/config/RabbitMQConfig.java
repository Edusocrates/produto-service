package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Fila e Exchange para produto -> estoque (produto criado)
    public static final String PRODUTO_CRIADO_QUEUE = "produto.criado.queue";
    public static final String PRODUTO_CRIADO_EXCHANGE = "produto.criado.exchange";
    public static final String PRODUTO_CRIADO_ROUTING_KEY = "produto.criado";

    // Fila e Exchange para preco -> produto (preco atualizado)
    public static final String PRECO_ATUALIZADO_QUEUE = "preco.atualizado.queue";
    public static final String PRECO_ATUALIZADO_EXCHANGE = "preco.atualizado.exchange";
    public static final String PRECO_ATUALIZADO_ROUTING_KEY = "preco.atualizado";

    // Fila e Exchange para pedido <-> produto (sincronização)
    public static final String PEDIDO_PRODUTO_SYNC_QUEUE = "pedido.produto.sync.queue";
    public static final String PEDIDO_PRODUTO_SYNC_EXCHANGE = "pedido.produto.sync.exchange";
    public static final String PEDIDO_PRODUTO_SYNC_ROUTING_KEY = "pedido.produto.sync";

    // === PRODUTO CRIADO ===
    @Bean
    public Queue produtoCriadoQueue() {
        return QueueBuilder.durable(PRODUTO_CRIADO_QUEUE).build();
    }

    @Bean
    public TopicExchange produtoCriadoExchange() {
        return new TopicExchange(PRODUTO_CRIADO_EXCHANGE);
    }

    @Bean
    public Binding produtoCriadoBinding() {
        return BindingBuilder
                .bind(produtoCriadoQueue())
                .to(produtoCriadoExchange())
                .with(PRODUTO_CRIADO_ROUTING_KEY);
    }

    // === PREÇO ATUALIZADO ===
    @Bean
    public Queue precoAtualizadoQueue() {
        return QueueBuilder.durable(PRECO_ATUALIZADO_QUEUE).build();
    }

    @Bean
    public TopicExchange precoAtualizadoExchange() {
        return new TopicExchange(PRECO_ATUALIZADO_EXCHANGE);
    }

    @Bean
    public Binding precoAtualizadoBinding() {
        return BindingBuilder
                .bind(precoAtualizadoQueue())
                .to(precoAtualizadoExchange())
                .with(PRECO_ATUALIZADO_ROUTING_KEY);
    }

    // === PEDIDO <-> PRODUTO SYNC ===
    @Bean
    public Queue pedidoProdutoSyncQueue() {
        return QueueBuilder.durable(PEDIDO_PRODUTO_SYNC_QUEUE).build();
    }

    @Bean
    public TopicExchange pedidoProdutoSyncExchange() {
        return new TopicExchange(PEDIDO_PRODUTO_SYNC_EXCHANGE);
    }

    @Bean
    public Binding pedidoProdutoSyncBinding() {
        return BindingBuilder
                .bind(pedidoProdutoSyncQueue())
                .to(pedidoProdutoSyncExchange())
                .with(PEDIDO_PRODUTO_SYNC_ROUTING_KEY);
    }

    @Bean
    public Queue pedidoSyncProdutoQueue() {
        return new Queue("pedido-produto-sync-queue", true);
    }
}
