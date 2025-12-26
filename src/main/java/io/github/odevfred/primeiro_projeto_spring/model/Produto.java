package io.github.odevfred.primeiro_projeto_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Indica que é um entidade JPA
@Table(name = "produtos") // Indica e define o nome da tabela
public class Produto {

    @Id // Indica que será um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que será uma PK e será gerada automaticamente
    private Long id;

    private String nome;
    private Double preco;

    // Construtor vazio necessário para JPA
    public Produto(){}

    // Construtor com parâmetros para facilitar a criação de produtos
    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Métodos getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    // Métodos setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
