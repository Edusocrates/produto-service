package com.fiap.rm358568.edusocrates.produto_service.dominio.gateway;

import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoGateway {

    Produto salvar(Produto produto);

    Optional<Produto> buscarPorId(UUID id);

    List<Produto> listarTodos();

    void deletarPorId(UUID id);

    boolean existePorId(UUID id);

    void processarVendaProduto(String produtoId, Integer quantidade);

    void atualizarEstoque(UUID id, Integer integer);
}
