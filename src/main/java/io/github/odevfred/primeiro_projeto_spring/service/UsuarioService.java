package io.github.odevfred.primeiro_projeto_spring.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.odevfred.primeiro_projeto_spring.model.Usuario;
import io.github.odevfred.primeiro_projeto_spring.repository.UsuarioRepository;

/**
 * Service responsável pela lógica de negócio de usuários
 * Gerencia registro e busca de usuários com criptografia de senha
 */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependência e inicialização do encoder de senha
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra um novo usuário com senha criptografada
     * @param username Nome de usuário
     * @param password Senha em texto puro (será criptografada)
     * @return Usuário registrado
     */
    public Usuario registrarUsuario(String username, String password) {
        // Criptografa a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca usuário por username
     * @param username Nome de usuário
     * @return Optional contendo o usuário se encontrado
     */
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
