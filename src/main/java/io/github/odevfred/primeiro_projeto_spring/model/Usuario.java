package io.github.odevfred.primeiro_projeto_spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade que representa um usuário do sistema
 * Armazena credenciais de login
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Único e não pode ser nulo
    private String username; // Nome de usuário para login

    @Column(nullable = false)
    private String password; // Senha criptografada

    // Construtor vazio necessário para JPA
    public Usuario() {}

    // Construtor com parâmetros para facilitar a criação de usuários
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Métodos getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Métodos setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
