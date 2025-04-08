package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;

import java.util.UUID;

public interface BuscarProdutoPorIdUseCase {
    ProdutoResponse executar(UUID id);
}