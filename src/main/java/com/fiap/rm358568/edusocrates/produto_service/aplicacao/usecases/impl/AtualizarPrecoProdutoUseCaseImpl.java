package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.API.exceptions.ProdutoNaoEncontradoException;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.AtualizarPrecoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AtualizarPrecoProdutoUseCaseImpl implements AtualizarPrecoProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public void atualizarPreco(UUID produtoId, BigDecimal novoPreco) {
        Produto produto = produtoGateway.buscarPorId(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        produto.atualizarPreco(novoPreco);
        produtoGateway.salvar(produto);
    }
}
