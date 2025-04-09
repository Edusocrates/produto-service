package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.ProcessarPedidoProdutoUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessarPedidoProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProcessarPedidoProdutoUseCaseImpl processarPedidoProdutoUseCase;

    @Test
    void executar_deveProcessarVendaParaCadaProduto() {
        Map<String, Integer> produtosQuantidade = Map.of(
                "produto1", 5,
                "produto2", 10
        );

        processarPedidoProdutoUseCase.executar(produtosQuantidade);

        verify(produtoGateway, times(1)).processarVendaProduto("produto1", 5);
        verify(produtoGateway, times(1)).processarVendaProduto("produto2", 10);
    }

    @Test
    void executar_naoDeveChamarGatewayQuandoMapaVazio() {
        Map<String, Integer> produtosQuantidade = Map.of();

        processarPedidoProdutoUseCase.executar(produtosQuantidade);

        verifyNoInteractions(produtoGateway);
    }
}