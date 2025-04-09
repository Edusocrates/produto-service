package com.fiap.rm358568.edusocrates.produto_service.API.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AtualizarProdutoRequest(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser positivo")
        BigDecimal preco,

        @NotNull(message = "Quantidade em estoque é obrigatória")
        @Positive(message = "Quantidade deve ser positiva")
        Integer quantidadeEmEstoque,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        String status,

        String categoria

) {}
