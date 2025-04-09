package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;


import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ConfirmarCriacaoProdutoNoEstoqueUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProdutoCriadoListener {

    private final ConfirmarCriacaoProdutoNoEstoqueUseCase useCase;

    public ProdutoCriadoListener(ConfirmarCriacaoProdutoNoEstoqueUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "estoque.produto.criado")
    public void listen(ProdutoCriadoEvent event) {
        useCase.executar(event);
    }
}
