package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.exceptions.ProdutoNaoEncontradoException;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.BuscarProdutoPorIdUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarProdutoPorIdUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private BuscarProdutoPorIdUseCaseImpl buscarProdutoPorIdUseCase;

    @Test
    void executar_deveRetornarProdutoResponse_quandoProdutoExistir() {
        UUID id = UUID.randomUUID();
        Produto produtoMock = new Produto(
                id,
                "Produto Teste",
                "Descrição Teste",
                BigDecimal.valueOf(100.0),
                "teste",
                123,
                "ATIVO",
                LocalDateTime.now()
        );

        when(produtoGateway.buscarPorId(id)).thenReturn(Optional.of(produtoMock));

        ProdutoResponse response = buscarProdutoPorIdUseCase.executar(id);

        verify(produtoGateway, times(1)).buscarPorId(id);
        assertEquals(id, response.id());
        assertEquals(produtoMock.getNome(), response.nome());
        assertEquals(produtoMock.getPreco(), response.preco());
        assertEquals(produtoMock.getQuantidadeEstoque(), response.quantidadeEmEstoque());
        assertEquals(produtoMock.getDescricao(), response.descricao());
    }

    @Test
    void executar_deveLancarExcecao_quandoProdutoNaoExistir() {
        UUID id = UUID.randomUUID();

        when(produtoGateway.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () ->
                buscarProdutoPorIdUseCase.executar(id)
        );

        verify(produtoGateway, times(1)).buscarPorId(id);
    }
}