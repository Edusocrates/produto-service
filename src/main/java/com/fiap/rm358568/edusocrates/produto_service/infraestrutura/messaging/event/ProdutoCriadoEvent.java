package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProdutoCriadoEvent(
        UUID id,
        String nome,
        BigDecimal preco,
        Integer quantidadeEstoque
) {}
