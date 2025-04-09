package com.fiap.rm358568.edusocrates.produto_service.API.controller;

import com.fiap.rm358568.edusocrates.produto_service.API.requests.AtualizarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.CriarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    private CriarProdutoUseCase criarProdutoUseCase;

    @Mock
    private BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;

    @Mock
    private ListarTodosProdutosUseCase buscarTodosProdutosUseCase;

    @Mock
    private AtualizarProdutoUseCase atualizarProdutoUseCase;

    @Mock
    private DeletarProdutoUseCase removerProdutoUseCase;

    @InjectMocks
    private ProdutoController produtoController;

    @Test
    void criar_deveRetornarProdutoResponse_quandoProdutoForCriado() {
        CriarProdutoRequest request = new CriarProdutoRequest("Produto A", BigDecimal.valueOf(100.0), 10, "teste", "Ativo", "Categoria A");
        ProdutoResponse responseMock = new ProdutoResponse(UUID.randomUUID(), "Produto A", BigDecimal.valueOf(100.0), 1, "teste");

        when(criarProdutoUseCase.executar(request)).thenReturn(responseMock);

        ResponseEntity<ProdutoResponse> response = produtoController.criar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void buscarPorId_deveRetornarProdutoResponse_quandoProdutoExistir() {
        UUID id = UUID.randomUUID();
        ProdutoResponse responseMock = new ProdutoResponse(UUID.randomUUID(), "Produto A", BigDecimal.valueOf(100.0), 1, "teste");

        when(buscarProdutoPorIdUseCase.executar(id)).thenReturn(responseMock);

        ResponseEntity<ProdutoResponse> response = produtoController.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void buscarTodos_deveRetornarListaDeProdutos_quandoProdutosExistirem() {
        ProdutoResponse responseMock = new ProdutoResponse(UUID.randomUUID(), "Produto A", BigDecimal.valueOf(100.0), 1, "teste");

        when(buscarTodosProdutosUseCase.executar()).thenReturn(List.of(responseMock));

        ResponseEntity<List<ProdutoResponse>> response = produtoController.buscarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(responseMock, response.getBody().get(0));
    }

    @Test
    void atualizar_deveRetornarProdutoResponse_quandoProdutoForAtualizado() {
        UUID id = UUID.randomUUID();
        AtualizarProdutoRequest request = new AtualizarProdutoRequest("Produto A", BigDecimal.valueOf(150.0), 20, "Descrição Atualizada", "Inativo", "Categoria B");
        ProdutoResponse responseMock = new ProdutoResponse(UUID.randomUUID(), "Produto A", BigDecimal.valueOf(100.0), 1, "teste");

        when(atualizarProdutoUseCase.executar(id, request)).thenReturn(responseMock);

        ResponseEntity<ProdutoResponse> response = produtoController.atualizar(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void remover_deveRetornarNoContent_quandoProdutoForRemovido() {
        UUID id = UUID.randomUUID();

        doNothing().when(removerProdutoUseCase).executar(id);

        ResponseEntity<Void> response = produtoController.remover(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(removerProdutoUseCase, times(1)).executar(id);
    }
}