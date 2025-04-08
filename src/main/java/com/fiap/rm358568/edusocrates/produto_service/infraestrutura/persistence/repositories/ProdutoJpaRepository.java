package com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.repositories;

import com.fiap.rm358568.edusocrates.produto_service.infraestrutura.persistence.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, UUID> {
}
