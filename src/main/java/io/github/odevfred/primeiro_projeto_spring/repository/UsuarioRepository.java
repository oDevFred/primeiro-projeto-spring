package io.github.odevfred.primeiro_projeto_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.odevfred.primeiro_projeto_spring.model.Usuario;

/**
 * Repository para operações de banco de dados da entidade Usuario
 * Herda métodos CRUD do JpaRepository e adiciona método customizado
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método customizado para buscar usuário por username
    Optional<Usuario> findByUsername(String username);
}
