package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl.ListarTodosProdutosUseCaseImpl;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarTodosProdutosUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ListarTodosProdutosUseCaseImpl listarTodosProdutosUseCase;

    @Test
    void executar_deveRetornarListaDeProdutosResponse() {
        Produto produto1 = new Produto(
                UUID.randomUUID(),
                "Produto 1",
                "Descrição 1",
                BigDecimal.valueOf(100.0),
                "Categoria 1",
                10,
                "Ativo",
                LocalDateTime.now()
        );

        Produto produto2 = new Produto(
                UUID.randomUUID(),
                "Produto 2",
                "Descrição 2",
                BigDecimal.valueOf(200.0),
                "Categoria 2",
                20,
                "Inativo",
                LocalDateTime.now()
        );

        when(produtoGateway.listarTodos()).thenReturn(List.of(produto1, produto2));

        List<ProdutoResponse> response = listarTodosProdutosUseCase.executar();

        verify(produtoGateway, times(1)).listarTodos();
        assertEquals(2, response.size());

        ProdutoResponse response1 = response.get(0);
        assertEquals(produto1.getId(), response1.id());
        assertEquals(produto1.getNome(), response1.nome());
        assertEquals(produto1.getPreco(), response1.preco());
        assertEquals(produto1.getQuantidadeEstoque(), response1.quantidadeEmEstoque());
        assertEquals(produto1.getDescricao(), response1.descricao());

        ProdutoResponse response2 = response.get(1);
        assertEquals(produto2.getId(), response2.id());
        assertEquals(produto2.getNome(), response2.nome());
        assertEquals(produto2.getPreco(), response2.preco());
        assertEquals(produto2.getQuantidadeEstoque(), response2.quantidadeEmEstoque());
        assertEquals(produto2.getDescricao(), response2.descricao());
    }
}