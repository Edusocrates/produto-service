package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PrecoAtualizadoEvent(
        UUID produtoId,
        BigDecimal novoPreco
) {}
