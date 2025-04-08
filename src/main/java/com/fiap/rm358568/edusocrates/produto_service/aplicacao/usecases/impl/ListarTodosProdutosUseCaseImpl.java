package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ListarTodosProdutosUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ListarTodosProdutosUseCaseImpl implements ListarTodosProdutosUseCase {

    private final ProdutoGateway produtoGateway;

    public ListarTodosProdutosUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public List<ProdutoResponse> executar() {
        log.info("Listando todos os produtos");
        return produtoGateway.listarTodos()
                .stream()
                .map(produto -> new ProdutoResponse(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidadeEstoque(),
                        produto.getDescricao()
                ))
                .collect(Collectors.toList());
    }
}
