package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.requests.CriarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.CriarProdutoUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.consumer.ProdutoMessagePublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private ProdutoMessagePublisher produtoMessagePublisher;

    @InjectMocks
    private CriarProdutoUseCaseImpl criarProdutoUseCase;

    @Test
    void executar_deveCriarProdutoComSucesso() {
        CriarProdutoRequest request = new CriarProdutoRequest(
                "Produto Teste",
                BigDecimal.valueOf(100.0),
                123,
                "TESTE",
                "Ativo",
                "Categoria Teste"
        );

        Produto produtoMock = Produto.builder()
                .id(UUID.randomUUID())
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .quantidadeEstoque(request.quantidadeEmEstoque())
                .status(request.status())
                .categoria(request.categoria())
                .build();

        Produto produtoSalvoMock = Produto.builder()
                .id(produtoMock.getId())
                .nome(produtoMock.getNome())
                .descricao(produtoMock.getDescricao())
                .preco(produtoMock.getPreco())
                .quantidadeEstoque(produtoMock.getQuantidadeEstoque())
                .status(produtoMock.getStatus())
                .categoria(produtoMock.getCategoria())
                .build();

        when(produtoGateway.salvar(any(Produto.class))).thenReturn(produtoSalvoMock);

        ProdutoResponse response = criarProdutoUseCase.executar(request);

        verify(produtoGateway, times(1)).salvar(any(Produto.class));
        verify(produtoMessagePublisher, times(1)).enviarMensagem("Produto cadastrado com sucesso: " + produtoMock.getNome());

        assertEquals(produtoSalvoMock.getId(), response.id());
        assertEquals(produtoSalvoMock.getNome(), response.nome());
        assertEquals(produtoSalvoMock.getPreco(), response.preco());
        assertEquals(produtoSalvoMock.getQuantidadeEstoque(), response.quantidadeEmEstoque());
        assertEquals(produtoSalvoMock.getDescricao(), response.descricao());
    }
}