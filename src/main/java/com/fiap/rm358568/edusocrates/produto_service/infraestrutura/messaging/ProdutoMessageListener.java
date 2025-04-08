package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.fiap.rm358568.edusocrates.produto_service.infraestrutura.config.RabbitMQConfig.*;

@Slf4j
@Component
public class ProdutoMessageListener {

    @RabbitListener(queues = PRODUTO_QUEUE)
    public void receberMensagem(String mensagem) {
        log.info("📥 ProdutoService recebeu mensagem: {}", mensagem);
    }
}