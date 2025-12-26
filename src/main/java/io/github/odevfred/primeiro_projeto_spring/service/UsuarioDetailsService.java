package io.github.odevfred.primeiro_projeto_spring.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.odevfred.primeiro_projeto_spring.model.Usuario;
import io.github.odevfred.primeiro_projeto_spring.repository.UsuarioRepository;

/**
 * Service que implementa UserDetailsService do Spring Security
 * Carrega dados do usuário para autenticação
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência via construtor
    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carrega os dados do usuário para autenticação
     * @param username Nome de usuário
     * @return UserDetails contendo dados do usuário
     * @throws UsernameNotFoundException se o usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca usuário no banco de dados
        Usuario usuario = usuarioRepository.findByUsername(username)
                                            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        
        // Converte para UserDetails do Spring Security
        return User.builder()
                    .username(usuario.getUsername())
                    .password(usuario.getPassword())
                    .roles("USER")
                    .build();
    }
    
}
