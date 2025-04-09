package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.adapters;

import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.entities.ProdutoEntity;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.repositories.ProdutoJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProdutoRepositoryAdapter implements ProdutoGateway {

    private final ProdutoJpaRepository repository;

    public ProdutoRepositoryAdapter(ProdutoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity entity = ProdutoEntity.fromDomain(produto);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<Produto> buscarPorId(UUID id) {
        return repository.findById(id).map(ProdutoEntity::toDomain);
    }

    @Override
    public List<Produto> listarTodos() {
        return repository.findAll().stream()
                .map(ProdutoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public void processarVendaProduto(String produtoId, Integer quantidade) {
        log.info("Processando venda do produto com ID: {} e quantidade: {}", produtoId, quantidade);

        ProdutoEntity produtoEntity = repository.findById(UUID.fromString(produtoId))
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Produto produto = produtoEntity.toDomain();

        log.info("Produto encontrado: {}. Estoque atual: {}", produto.getNome(), produto.getQuantidadeEstoque());
        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new RuntimeException("Quantidade em estoque insuficiente!");
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            ProdutoEntity atualizadoEntity = ProdutoEntity.fromDomain(produto);
            repository.save(atualizadoEntity);
            log.info("Venda processada com sucesso! Produto ID: {}. Estoque restante: {}", produtoId, produto.getQuantidadeEstoque());
        }
    }
}
