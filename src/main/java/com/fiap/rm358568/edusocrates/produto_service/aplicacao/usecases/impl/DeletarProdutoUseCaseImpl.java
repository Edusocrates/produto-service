package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.DeletarProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class DeletarProdutoUseCaseImpl implements DeletarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public DeletarProdutoUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public void executar(UUID id) {
        log.info("Iniciando o processo de deleção do produto com ID: {}", id);
        produtoGateway.deletarPorId(id);
        log.info("Produto deletado com sucesso! ID: {}", id);
    }
}
