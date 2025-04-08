package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import java.math.BigDecimal;
import java.util.UUID;

public interface AtualizarPrecoProdutoUseCase {
    void atualizarPreco(UUID produtoId, BigDecimal novoPreco);
}
