package io.github.odevfred.primeiro_projeto_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.odevfred.primeiro_projeto_spring.model.Usuario;
import io.github.odevfred.primeiro_projeto_spring.security.JwtUtil;
import io.github.odevfred.primeiro_projeto_spring.service.UsuarioService;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller responsável pela autenticação de usuários
 * Gerencia endpoints de registro e login
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    // Injeção de dependência via construtor
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para registrar um novo usuário
     * @param request Map contendo username e password
     * @return Usuário registrado
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        Usuario usuario = usuarioService.registrarUsuario(request.get("username"), "password");
        return ResponseEntity.ok(usuario);
    }
    
    /**
     * Endpoint para fazer login
     * @param request Map contendo username e password
     * @return Token JWT em caso de sucesso, ou erro 401
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        // Busca o usuário pelo username
        Optional<Usuario> usuario = usuarioService.buscarPorUsername(request.get("username"));

        // Valida se o usuário existe e se a senha está correta
        if (usuario.isPresent() && usuario.get().getPassword().equals(request.get("password"))) {
            // Gera token JWT para o usuário autenticado
            String token = JwtUtil.generateToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }

        // Retorna erro 401 se as credenciais forem inválidas
        return ResponseEntity.status(401).body("Credenciais Inválidas");
    }
    
}
