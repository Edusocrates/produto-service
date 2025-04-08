package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.requests.AtualizarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;

import java.util.UUID;

public interface AtualizarProdutoUseCase {
    ProdutoResponse executar(UUID id, AtualizarProdutoRequest request);
}
