package io.github.odevfred.primeiro_projeto_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.odevfred.primeiro_projeto_spring.exceptions.RecursoNaoEncontradoException;
import io.github.odevfred.primeiro_projeto_spring.model.Produto;
import io.github.odevfred.primeiro_projeto_spring.repository.ProdutoRepository;

/**
 * Service responsável pela lógica de negócio de produtos
 * Intermedia as operações entre controller e repository
 */
@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    // Injeção de dependência via construtor
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Lista todos os produtos cadastrados
     * @return Lista de produtos
     */
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    /**
     * Busca produto por ID
     * @param id ID do produto
     * @return Produto encontrado
     * @throws RecursoNaoEncontradoException se não encontrar
     */
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado."));
    }

    /**
     * Salva um novo produto ou atualiza existente
     * @param produto Produto a ser salvo
     * @return Produto salvo
     */
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    /**
     * Deleta um produto por ID
     * @param id ID do produto a ser deletado
     * @throws RecursoNaoEncontradoException se o produto não existir
     */
    public void deletarProduto(Long id){

        // Verifica se o produto existe antes de deletar
        if(!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado.");
        }

        produtoRepository.deleteById(id);;
    }
}
