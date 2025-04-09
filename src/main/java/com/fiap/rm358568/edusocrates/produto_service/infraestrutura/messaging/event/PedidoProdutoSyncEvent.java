package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PedidoProdutoSyncEvent {

    private String pedidoId;
    private List<ItemPedido> itens;

    @Data
    public static class ItemPedido {
        private String produtoId;
        private int quantidade;

    }
}
