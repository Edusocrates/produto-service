package com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.impl;

import com.fiap.rm358568.edusocrates.produto_service.API.requests.CriarProdutoRequest;
import com.fiap.rm358568.edusocrates.produto_service.API.responses.ProdutoResponse;
import com.fiap.rm358568.edusocrates.produto_service.aplicacao.usecases.CriarProdutoUseCase;
import com.fiap.rm358568.edusocrates.produto_service.dominio.entities.Produto;
import com.fiap.rm358568.edusocrates.produto_service.dominio.gateway.ProdutoGateway;
import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.messaging.ProdutoMessagePublisher;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Transactional
public class CriarProdutoUseCaseImpl implements CriarProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    private final ProdutoMessagePublisher produtoMessagePublisher;

    public CriarProdutoUseCaseImpl(ProdutoGateway produtoGateway, ProdutoMessagePublisher produtoMessagePublisher) {
        this.produtoGateway = produtoGateway;
        this.produtoMessagePublisher = produtoMessagePublisher;
    }

    @Override
    public ProdutoResponse executar(CriarProdutoRequest request) {
        log.info("Iniciando o cadastro do produto: {}", request.nome());
        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .quantidadeEstoque(request.quantidadeEmEstoque())
                .status(request.status())
                .categoria(request.categoria())
                .build();

        log.info("Enviando produto para o gateway: {}", produto.getNome());
        Produto produtoSalvo = produtoGateway.salvar(produto);

        log.info("Produto salvo com sucesso: {}", produtoSalvo.getNome());
        log.info("Enviando mensagem para o tópico de produtos: {}", produto.getNome());
        produtoMessagePublisher.enviarMensagem("Produto cadastrado com sucesso: " + produto.getNome());

        log.info("Mensagem enviada com sucesso para o tópico de produtos: {}", produto.getNome());
        return new ProdutoResponse(
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getPreco(),
                produtoSalvo.getQuantidadeEstoque(),
                produtoSalvo.getDescricao()
        );
    }
}
