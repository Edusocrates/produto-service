package com.fiap.rm358568.edusocrates.produto_service.API.responses;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProdutoResumoResponse(

        UUID id,
        String nome,
        BigDecimal preco

) {}
