package io.github.odevfred.primeiro_projeto_spring.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro que intercepta requisições HTTP para validar token JWT
 * Executa uma vez por requisição e autentica o usuário se o token for válido
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;

    // Injeção de dependência via construtor
    public JwtAuthFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Filtra cada requisição HTTP para validar autenticação JWT
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Obtém o cabeçalho Authorization
        String authHeader = request.getHeader("Authorization");
        // Se não houver cabeçalho ou não começar com "Bearer", continua sem autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove o prefixo "Bearer " para obter apenas o token
        String token = authHeader.substring(7);
        // Extrai o username do token
        String username = JwtUtil.extractUsername(token);

        // Se o username existe e ainda não há autenticação no contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Carrega os detalhes do usuário
            UserDetails userdetails = userDetailsService.loadUserByUsername(username);
            // Se o token for válido, autentica o usuário
            if (JwtUtil.validateToken(token)) {
                // Cria objeto de autenticação
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userdetails, null, userdetails.getAuthorities()
                );
                // Define a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
    }


}
