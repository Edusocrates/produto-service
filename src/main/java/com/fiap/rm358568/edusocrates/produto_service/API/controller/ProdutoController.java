package com.fiap.rm358568.edusocrates.produto_service.API.controller;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.*;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.CriarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.AtualizarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Configurações Produto", description = "Operações relacionadas as configurações e manipulação de produtos")
public class ProdutoController {

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;
    private final ListarTodosProdutosUseCase buscarTodosProdutosUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final DeletarProdutoUseCase removerProdutoUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name = "Criar Produto", description = "Cria um novo produto")
    @Operation(summary = "Cria um novo produto", description = "Cria um novo produto com os dados fornecidos")
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody CriarProdutoRequest request) {
        log.info("Criando produto: {}", request);
        ProdutoResponse response = criarProdutoUseCase.executar(request);
        return ResponseEntity.created( URI.create("localhost:8082/produto-service")).body(response);
    }

    @GetMapping("/{id}")
    @Tag(name = "Buscar Produto por ID", description = "Busca um produto pelo ID")
    @Operation(summary = "Busca um produto pelo ID", description = "Busca um produto pelo ID fornecido")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable UUID id) {
        log.info("Buscando produto por ID: {}", id);
        ProdutoResponse response = buscarProdutoPorIdUseCase.executar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Tag(name = "Listar Produtos", description = "Lista todos os produtos")
    @Operation(summary = "Lista todos os produtos", description = "Lista todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoResponse>> buscarTodos() {
        log.info("Listando todos os produtos!");
        List<ProdutoResponse> responseList = buscarTodosProdutosUseCase.executar();
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    @Tag(name = "Atualizar Produto", description = "Atualiza um produto existente")
    @Operation(summary = "Atualiza um produto existente", description = "Atualiza um produto existente com os dados fornecidos")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody AtualizarProdutoRequest request) {
        log.info("Atualizando produto com ID: {}", id);
        ProdutoResponse response = atualizarProdutoUseCase.executar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Remover Produto", description = "Remove um produto existente")
    @Operation(summary = "Remove um produto existente", description = "Remove um produto existente com o ID fornecido")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        log.info("Removendo produto com ID: {}", id);
        removerProdutoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}