package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.entities;

import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private String categoria;

    private Integer quantidadeEstoque;

    private String status;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    public static ProdutoEntity fromDomain(Produto produto) {
        return ProdutoEntity.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .categoria(produto.getCategoria())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .status(produto.getStatus())
                .criadoEm(LocalDateTime.now())
                .build();
    }

    public static ProdutoEntity novoProdutoDomain(Produto produto) {
        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .categoria(produto.getCategoria())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .status(produto.getStatus())
                .criadoEm(LocalDateTime.now())
                .build();
    }

    public Produto toDomain() {
        return new Produto(id, nome, descricao, preco, categoria, quantidadeEstoque, status, criadoEm);
    }
}