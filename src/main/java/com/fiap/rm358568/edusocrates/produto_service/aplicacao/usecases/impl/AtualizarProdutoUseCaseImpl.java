package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.API.exceptions.ProdutoNaoEncontradoException;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.AtualizarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.AtualizarProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Transactional
public class AtualizarProdutoUseCaseImpl implements AtualizarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public AtualizarProdutoUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public ProdutoResponse executar(UUID id, AtualizarProdutoRequest request) {
        log.info("Iniciando o processo de atualização do produto com ID: {}", id);
        Produto produtoExistente = produtoGateway.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado!"));

        produtoExistente.atualizar(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.categoria(),
                request.quantidadeEmEstoque(),
                request.status()
        );

        log.info("Produto encontrado, iniciando a atualização do produto com ID: {}", id);
        Produto atualizado = produtoGateway.salvar(produtoExistente);

        log.info("Produto atualizado com sucesso! ID: {}", atualizado.getId());
        return new ProdutoResponse(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getQuantidadeEstoque(),
                atualizado.getDescricao()
        );
    }
}