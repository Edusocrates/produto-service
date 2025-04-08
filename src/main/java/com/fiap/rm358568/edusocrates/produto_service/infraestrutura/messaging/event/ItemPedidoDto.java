package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ItemPedidoDto(
        UUID produtoId,
        int quantidade
) {}