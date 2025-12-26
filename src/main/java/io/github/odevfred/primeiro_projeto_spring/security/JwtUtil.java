package io.github.odevfred.primeiro_projeto_spring.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Classe utilitária para manipulação de tokens JWT
 * Responsável por gerar, validar e extrair informações dos tokens
 */
public class JwtUtil {
    // Chave secreta para assinar os tokens
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Tempo de expiração do token: 1 dia em milissegundos
    private static final long EXPIRATION_TIME = 86400000; // 1 dia

    /**
     * Gera um token JWT para o usuário
     * @param username Nome de usuário
     * @return Token JWT gerado
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
    }

    /**
     * Extrai o username do token JWT
     * @param token Token JWT
     * @return Username contido no token
     */
    public static String extractUsername(String token) {
        return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }

    /**
     * Valida se o token JWT é válido
     * @param token Token JWT a ser validado
     * @return true se válido, false caso contrário
     */
    public static boolean validateToken(String token) {
        try {
            // Tenta fazer o parse do token - se conseguir, é válido
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Token inválido ou expirado
            return false;
        }
    }
}
