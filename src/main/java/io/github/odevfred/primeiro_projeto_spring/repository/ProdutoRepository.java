package io.github.odevfred.primeiro_projeto_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.odevfred.primeiro_projeto_spring.model.Produto;

/**
 * Repository para operações de banco de dados da entidade Produto
 * Herda métodos CRUD do JpaRepository
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
