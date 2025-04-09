package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.ConfirmarCriacaoProdutoNoEstoqueUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.ProdutoCriadoEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmarCriacaoProdutoNoEstoqueUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ConfirmarCriacaoProdutoNoEstoqueUseCaseImpl confirmarCriacaoProdutoNoEstoqueUseCase;

    @Test
    void executar_deveAtualizarEstoqueComSucesso() {
        ProdutoCriadoEvent event = new ProdutoCriadoEvent(
                UUID.randomUUID(),
                "Produto Teste",
                BigDecimal.valueOf(123.45),
                50
        );

        confirmarCriacaoProdutoNoEstoqueUseCase.executar(event);

        verify(produtoGateway, times(1)).atualizarEstoque(event.id(), event.quantidadeEstoque());
    }
}