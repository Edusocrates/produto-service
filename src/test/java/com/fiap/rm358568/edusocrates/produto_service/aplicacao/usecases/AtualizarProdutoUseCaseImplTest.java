package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.exceptions.ProdutoNaoEncontradoException;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.AtualizarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.AtualizarProdutoUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private AtualizarProdutoUseCaseImpl atualizarProdutoUseCase;

    @Test
    void executar_deveAtualizarProdutoQuandoProdutoExistir() {
        UUID id = UUID.randomUUID();
        AtualizarProdutoRequest request = new AtualizarProdutoRequest(
                "Produto Atualizado",
                BigDecimal.valueOf(200.0),
                123,
                "Categoria Atualizada",
                "teste",
                "Ativo"
        );
        Produto produtoMock = mock(Produto.class);
        Produto produtoAtualizadoMock = mock(Produto.class);

        when(produtoGateway.buscarPorId(id)).thenReturn(Optional.of(produtoMock));
        when(produtoGateway.salvar(produtoMock)).thenReturn(produtoAtualizadoMock);
        when(produtoAtualizadoMock.getId()).thenReturn(id);
        when(produtoAtualizadoMock.getNome()).thenReturn(request.nome());
        when(produtoAtualizadoMock.getPreco()).thenReturn(request.preco());
        when(produtoAtualizadoMock.getQuantidadeEstoque()).thenReturn(request.quantidadeEmEstoque());
        when(produtoAtualizadoMock.getDescricao()).thenReturn(request.descricao());

        ProdutoResponse response = atualizarProdutoUseCase.executar(id, request);

        verify(produtoMock, times(1)).atualizar(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.categoria(),
                request.quantidadeEmEstoque(),
                request.status()
        );
        verify(produtoGateway, times(1)).salvar(produtoMock);

        assertEquals(id, response.id());
        assertEquals(request.nome(), response.nome());
        assertEquals(request.preco(), response.preco());
        assertEquals(request.quantidadeEmEstoque(), response.quantidadeEmEstoque());
        assertEquals(request.descricao(), response.descricao());
    }

    @Test
    void executar_deveLancarExcecaoQuandoProdutoNaoExistir() {
        UUID id = UUID.randomUUID();
        AtualizarProdutoRequest request = new AtualizarProdutoRequest(
                "Produto Atualizado",
                BigDecimal.valueOf(200.0),
                123,
                "Categoria Atualizada",
                "teste",
                "Ativo"
        );

        when(produtoGateway.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () ->
                atualizarProdutoUseCase.executar(id, request)
        );

        verify(produtoGateway, never()).salvar(any());
    }
}