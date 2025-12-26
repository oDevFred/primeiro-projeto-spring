
# Primeiro Projeto Spring

Aplicação de exemplo em Spring Boot que implementa autenticação JWT simples e operações CRUD para produtos.

## Descrição

Este projeto é uma API REST construída com Spring Boot que fornece endpoints para registro/login de usuário (autenticação básica) e gerenciamento de `Produto` (listar, buscar, criar e deletar).

## Tecnologias

- Java 17+
- Spring Boot
- Spring Web
- Maven (com `mvnw` wrapper)

## Estrutura do projeto

- `src/main/java/.../controller` — controladores REST (`AuthController`, `ProdutoController`)
- `src/main/java/.../service` — lógica de negócio (`UsuarioService`, `ProdutoService`)
- `src/main/java/.../model` — modelos/entidades (`Usuario`, `Produto`)
- `src/main/java/.../security` — utilitários e filtro JWT (`JwtUtil`, `JwtAuthFilter`, `SecurityConfig`)

## Endpoints

- Autenticação (`/auth`)
	- `POST /auth/register` — registra um usuário. Corpo esperado (JSON): `{ "username": "nome", "password": "senha" }`.
	- `POST /auth/login` — realiza login. Corpo esperado (JSON): `{ "username": "nome", "password": "senha" }`. Retorna `{ "token": "<jwt>" }` em caso de sucesso.

- Produtos (`/api/produtos`)
	- `GET /api/produtos` — lista todos os produtos.
	- `GET /api/produtos/{id}` — busca produto por `id`.
	- `POST /api/produtos` — cria um produto. Enviar JSON do `Produto` no corpo.
	- `DELETE /api/produtos/{id}` — deleta produto por `id`.

Observação: A autenticação JWT é utilizada em parte do projeto; ver `SecurityConfig` e `JwtAuthFilter` para detalhes de proteção de rotas.

## Pré-requisitos

- JDK 17 ou superior
- Maven (opcional, o projeto inclui o wrapper `mvnw`)

## Executando localmente

Usando o wrapper (recomendado):

```bash
./mvnw spring-boot:run
```

Ou fazendo build e executando o jar:

```bash
./mvnw package
java -jar target/*.jar
```

## Testes

Executar testes com:

```bash
./mvnw test
```

## Configurações

As propriedades de configuração ficam em `src/main/resources/application.properties`.

## Observações sobre segurança

O projeto contém uma implementação simples de JWT para ilustração. Em produção, não armazene senhas em texto puro e use estratégia adequada de hashing (BCrypt), além de validações e tratamento de exceções mais robustos.

## Créditos

Este projeto foi desenvolvido junto com a aula do professor Matheus Leandro: https://www.youtube.com/watch?v=SqU9v_V32RA

## Contribuição

Contribuições são bem-vindas. Abra uma issue descrevendo a proposta ou envie um pull request.
