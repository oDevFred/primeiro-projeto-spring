package io.github.odevfred.primeiro_projeto_spring.exceptions;

/**
 * Exceção customizada para indicar que um recurso não foi encontrado
 * Lançada quando uma busca por ID não retorna resultados
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    // Construtor que recebe mensagem de erro customizada
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
