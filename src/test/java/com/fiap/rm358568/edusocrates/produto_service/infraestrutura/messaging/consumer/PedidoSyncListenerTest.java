package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ProcessarPedidoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.PedidoProdutoSyncEvent;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.PedidoProdutoSyncEvent.ItemPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoSyncListenerTest {

    @Mock
    private ProcessarPedidoProdutoUseCase processarPedidoProdutoUseCase;

    @InjectMocks
    private PedidoSyncListener pedidoSyncListener;

    @Test
    void listen_deveProcessarPedidoComSucesso() {
        ItemPedido item1 = new ItemPedido();
        item1.setProdutoId("produto1");
        item1.setQuantidade(5);

        ItemPedido item2 = new ItemPedido();
        item2.setProdutoId("produto2");
        item2.setQuantidade(10);

        PedidoProdutoSyncEvent event = new PedidoProdutoSyncEvent();
        event.setItens(List.of(item1, item2));

        pedidoSyncListener.listen(event);

        Map<String, Integer> expectedProdutosQuantidade = Map.of(
                "produto1", 5,
                "produto2", 10
        );

        verify(processarPedidoProdutoUseCase, times(1)).executar(expectedProdutosQuantidade);
    }

    @Test
    void listen_naoDeveChamarUseCaseQuandoEventoSemItens() {
        PedidoProdutoSyncEvent event = new PedidoProdutoSyncEvent();
        event.setItens(List.of());

        pedidoSyncListener.listen(event);

        verifyNoInteractions(processarPedidoProdutoUseCase);
    }
}