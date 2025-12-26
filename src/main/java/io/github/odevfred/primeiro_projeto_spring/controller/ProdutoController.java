package io.github.odevfred.primeiro_projeto_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.odevfred.primeiro_projeto_spring.model.Produto;
import io.github.odevfred.primeiro_projeto_spring.service.ProdutoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller responsável pelas operações CRUD de produtos
 * Gerencia endpoints para listar, buscar, criar e deletar produtos
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    // Injeção de dependência via construtor
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Lista todos os produtos cadastrados
     * @return Lista de produtos
     */
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    /**
     * Busca um produto específico por ID
     * @param id ID do produto
     * @return Produto encontrado ou exception se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
            Produto produto = produtoService.buscarPorId(id);
            return ResponseEntity.ok(produto);
    }

    /**
     * Cria um novo produto
     * @param produto Dados do produto a ser criado
     * @return Produto criado
     */
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }
    
    /**
     * Deleta um produto por ID
     * @param id ID do produto a ser deletado
     * @return Resposta sem conteúdo (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    
}
