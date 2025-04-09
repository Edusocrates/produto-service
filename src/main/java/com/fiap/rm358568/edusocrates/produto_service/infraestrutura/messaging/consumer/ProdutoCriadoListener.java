package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;


import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ConfirmarCriacaoProdutoNoEstoqueUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
/**
 * Classe responsável por escutar eventos de criação de produtos e acionar o caso de uso correspondente.
 */
@Slf4j
public class ProdutoCriadoListener {

    private final ConfirmarCriacaoProdutoNoEstoqueUseCase useCase;

    public ProdutoCriadoListener(ConfirmarCriacaoProdutoNoEstoqueUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "estoque.produto.criado")
    public void listen(ProdutoCriadoEvent event) {
        log.info("Escutando evento de produto criado: {}", event);
        if (event == null) {
            return;
        }
        useCase.executar(event);
    }
}
