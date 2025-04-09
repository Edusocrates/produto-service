package com.fiap.rm358568.edusocrates.produto_service.dominio.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Produto {

    private  UUID id;
    private  String nome;
    private  String descricao;
    private  BigDecimal preco;
    private  Integer quantidadeEstoque;

    public Produto(UUID id, String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;

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
    }

    public void atualizar(String nome, String descricao, BigDecimal preco, int quantidadeEstoque) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (descricao != null && !descricao.isBlank()) {
            this.descricao = descricao;
        }
        if (preco != null && preco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = preco;
        }
        if (quantidadeEstoque >= 0) {
            this.quantidadeEstoque = quantidadeEstoque;
        }
    }

    public void atualizarPreco(BigDecimal novoPreco) {
        if (novoPreco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        this.preco = novoPreco;
    }

}
