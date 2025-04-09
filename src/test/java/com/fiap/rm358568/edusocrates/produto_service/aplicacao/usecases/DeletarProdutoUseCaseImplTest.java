package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.DeletarProdutoUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private DeletarProdutoUseCaseImpl deletarProdutoUseCase;

    @Test
    void executar_deveDeletarProdutoComSucesso() {
        UUID id = UUID.randomUUID();

        deletarProdutoUseCase.executar(id);

        verify(produtoGateway, times(1)).deletarPorId(id);
    }
}