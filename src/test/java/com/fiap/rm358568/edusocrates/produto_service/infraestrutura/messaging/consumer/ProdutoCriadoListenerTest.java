package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.ConfirmarCriacaoProdutoNoEstoqueUseCase;
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
class ProdutoCriadoListenerTest {

    @Mock
    private ConfirmarCriacaoProdutoNoEstoqueUseCase useCase;

    @InjectMocks
    private ProdutoCriadoListener listener;

    @Test
    void listen_deveExecutarUseCaseComEventoValido() {
        ProdutoCriadoEvent event = new ProdutoCriadoEvent(UUID.randomUUID(), "nomeProduto", BigDecimal.valueOf(100.0), 10);

        listener.listen(event);

        verify(useCase, times(1)).executar(event);
    }

    @Test
    void listen_naoDeveChamarUseCaseQuandoEventoForNulo() {
        listener.listen(null);

        verifyNoInteractions(useCase);
    }
}