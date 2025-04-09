package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.exceptions.ProdutoNaoEncontradoException;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.AtualizarPrecoProdutoUseCaseImpl;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarPrecoProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private AtualizarPrecoProdutoUseCaseImpl atualizarPrecoProdutoUseCase;

    @Test
    void atualizarPreco_deveAtualizarPrecoQuandoProdutoExistir() {
        UUID produtoId = UUID.randomUUID();
        BigDecimal novoPreco = BigDecimal.valueOf(150.0);
        Produto produtoMock = mock(Produto.class);

        when(produtoGateway.buscarPorId(produtoId)).thenReturn(Optional.of(produtoMock));

        atualizarPrecoProdutoUseCase.atualizarPreco(produtoId, novoPreco);

        verify(produtoMock, times(1)).atualizarPreco(novoPreco);
        verify(produtoGateway, times(1)).salvar(produtoMock);
    }

    @Test
    void atualizarPreco_deveLancarExcecaoQuandoProdutoNaoExistir() {
        UUID produtoId = UUID.randomUUID();
        BigDecimal novoPreco = BigDecimal.valueOf(150.0);

        when(produtoGateway.buscarPorId(produtoId)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () ->
                atualizarPrecoProdutoUseCase.atualizarPreco(produtoId, novoPreco)
        );

        verify(produtoGateway, never()).salvar(any());
    }
}