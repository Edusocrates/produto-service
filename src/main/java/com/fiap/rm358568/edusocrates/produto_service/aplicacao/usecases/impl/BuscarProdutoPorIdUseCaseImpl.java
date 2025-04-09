package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.API.exceptions.ProdutoNaoEncontradoException;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.BuscarProdutoPorIdUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BuscarProdutoPorIdUseCaseImpl implements BuscarProdutoPorIdUseCase {

    private final ProdutoGateway produtoGateway;

    public BuscarProdutoPorIdUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public ProdutoResponse executar(UUID id) {
        log.info("Buscando produto com ID: {}", id);

        Produto produto = produtoGateway.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getDescricao()
        );
    }
}
