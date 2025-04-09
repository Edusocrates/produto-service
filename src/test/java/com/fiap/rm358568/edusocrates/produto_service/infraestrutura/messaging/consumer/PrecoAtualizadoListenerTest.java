package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.AtualizarPrecoProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.event.PrecoAtualizadoEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrecoAtualizadoListenerTest {

    @Mock
    private AtualizarPrecoProdutoUseCase atualizarPrecoUseCase;

    @InjectMocks
    private PrecoAtualizadoListener precoAtualizadoListener;

    @Test
    void ouvirPrecoAtualizado_deveAtualizarPrecoComSucesso() {
        UUID produtoId = UUID.randomUUID(); // Gera o UUID uma vez e reutiliza
        BigDecimal preco = BigDecimal.valueOf(99.99);
        PrecoAtualizadoEvent event = new PrecoAtualizadoEvent(produtoId, preco);

        precoAtualizadoListener.ouvirPrecoAtualizado(event);

        verify(atualizarPrecoUseCase, times(1)).atualizarPreco(produtoId, preco); // Usa o mesmo UUID e preço
    }

    @Test
    void ouvirPrecoAtualizado_naoDeveChamarUseCaseQuandoEventoNulo() {
        precoAtualizadoListener.ouvirPrecoAtualizado(null);

        verifyNoInteractions(atualizarPrecoUseCase);
    }
}