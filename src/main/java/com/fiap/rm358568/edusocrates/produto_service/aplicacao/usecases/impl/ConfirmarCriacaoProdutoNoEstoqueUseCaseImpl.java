package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ConfirmarCriacaoProdutoNoEstoqueUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmarCriacaoProdutoNoEstoqueUseCaseImpl implements ConfirmarCriacaoProdutoNoEstoqueUseCase {

    private final ProdutoGateway produtoGateway;

    public ConfirmarCriacaoProdutoNoEstoqueUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public void executar(ProdutoCriadoEvent event) {
        log.info("Produto criado com sucesso! ID: {}, Nome: {}, Preço: {}, Quantidade: {}",
                event.id(), event.nome(), event.preco(), event.quantidadeEstoque());

        produtoGateway.atualizarEstoque(event.id(), event.quantidadeEstoque());
        log.info("Estoque atualizado com sucesso para o produto ID: {}", event.id());

    }
}
