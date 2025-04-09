package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import java.util.Map;

public interface ProcessarPedidoProdutoUseCase {

    void executar(Map<String, Integer> produtosQuantidade);
}
