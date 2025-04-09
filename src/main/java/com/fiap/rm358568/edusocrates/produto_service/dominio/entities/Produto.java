package com.fiap.rm358568.edusocrates.produto_service.dominio.entities;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Produto {

    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private Integer quantidadeEstoque;
    private String status;
    private LocalDateTime criadoEm;

    public Produto(UUID id, String nome, String descricao, BigDecimal preco, String categoria, Integer quantidadeEstoque, String status, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.status = status;
        this.criadoEm = criadoEm;

        validar();
    }

    private void validar() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
        if (quantidadeEstoque == null || quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Estoque inválido");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status é obrigatório");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("Categoria é obrigatória"); // Validação do campo
        }
    }

    public void atualizar(String nome, String descricao, BigDecimal preco, String categoria, int quantidadeEstoque, String status) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (descricao != null && !descricao.isBlank()) {
            this.descricao = descricao;
        }
        if (preco != null && preco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = preco;
        }
        if (categoria != null && !categoria.isBlank()) {
            this.categoria = categoria;
        }
        if (quantidadeEstoque >= 0) {
            this.quantidadeEstoque = quantidadeEstoque;
        }
        if (status != null && !status.isBlank()) {
            this.status = status;
        }
    }

    public void atualizarPreco(BigDecimal novoPreco) {
        if (novoPreco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        this.preco = novoPreco;
    }
}