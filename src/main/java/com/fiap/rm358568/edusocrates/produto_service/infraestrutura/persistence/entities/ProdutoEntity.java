package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.entities;

import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    private Integer quantidadeEstoque;

    public static ProdutoEntity fromDomain(Produto produto) {
        return ProdutoEntity.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .build();
    }

    public Produto toDomain() {
        return new Produto(id, nome, descricao, preco, quantidadeEstoque);
    }
}
