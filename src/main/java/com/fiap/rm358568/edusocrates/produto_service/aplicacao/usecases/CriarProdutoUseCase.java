package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.requests.CriarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;

public interface CriarProdutoUseCase {
    ProdutoResponse executar(CriarProdutoRequest request);
}
