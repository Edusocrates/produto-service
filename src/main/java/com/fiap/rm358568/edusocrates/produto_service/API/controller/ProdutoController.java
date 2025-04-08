package com.fiap.rm358568.edusocrates.produto_service.API.controller;

import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.*;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.CriarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.requests.AtualizarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;
    private final ListarTodosProdutosUseCase buscarTodosProdutosUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final DeletarProdutoUseCase removerProdutoUseCase;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody CriarProdutoRequest request) {
        log.info("Criando produto: {}", request);
        ProdutoResponse response = criarProdutoUseCase.executar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable UUID id) {
        log.info("Buscando produto por ID: {}", id);
        ProdutoResponse response = buscarProdutoPorIdUseCase.executar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscarTodos() {
        log.info("Listando todos os produtos!");
        List<ProdutoResponse> responseList = buscarTodosProdutosUseCase.executar();
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody AtualizarProdutoRequest request) {
        log.info("Atualizando produto com ID: {}", id);
        ProdutoResponse response = atualizarProdutoUseCase.executar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        log.info("Removendo produto com ID: {}", id);
        removerProdutoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}