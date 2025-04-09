package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ProcessarPedidoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ProcessarPedidoProdutoUseCaseImpl implements ProcessarPedidoProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public ProcessarPedidoProdutoUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public void executar(Map<String, Integer> produtosQuantidade) {
        produtosQuantidade.forEach((produtoId, quantidade) -> {
            // Lógica: aqui você pode por exemplo somar à quantidade vendida
            produtoGateway.processarVendaProduto(produtoId, quantidade);
        });
    }
}
