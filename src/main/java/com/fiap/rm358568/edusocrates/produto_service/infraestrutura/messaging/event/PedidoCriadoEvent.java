package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record PedidoCriadoEvent(
        UUID pedidoId,
        List<ItemPedidoDto> itens
) {}

