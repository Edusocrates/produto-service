package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;

public interface ConfirmarCriacaoProdutoNoEstoqueUseCase {
    void executar(ProdutoCriadoEvent event);
}
