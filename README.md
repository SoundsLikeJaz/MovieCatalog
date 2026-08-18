# 🎬 Sistema de Catálogo e Avaliação de Filmes

Projeto desenvolvido para a disciplina de **Engenharia de Software Escaláveis — Microsserviços e DevOps com Spring Boot e Spring Cloud**.

O sistema consiste em uma arquitetura baseada em microsserviços para gerenciamento de filmes e suas respectivas 
avaliações, utilizando **Spring Boot**, **Spring Cloud**, **Eureka**, **API Gateway**, **OpenFeign**, **MySQL** e 
**MongoDB**.

---

## 📌 Tema do projeto

Sistema distribuído para gerenciamento de um catálogo de filmes e suas avaliações.

A aplicação é dividida em diferentes microsserviços, permitindo que cada serviço seja responsável por um domínio específico e mantenha seus próprios dados.

---

## 🎯 Problema que o sistema resolve

O sistema permite:

- cadastrar filmes;
- consultar filmes cadastrados;
- atualizar informações de filmes;
- remover filmes;
- cadastrar avaliações de usuários;
- consultar avaliações;
- consultar avaliações de um filme;
- calcular a média de avaliações de um filme;
- consultar avaliações de um filme ordenadas pela nota.

Além disso, os microsserviços se comunicam entre si para disponibilizar informações relacionadas a filmes e avaliações.

---

# 🏗️ Arquitetura

A aplicação utiliza uma arquitetura baseada em microsserviços.

```text
                         ┌─────────────────────┐
                         │     Cliente         │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    API Gateway      │
                         │      :8080          │
                         └──────────┬──────────┘
                                    │
                         ┌──────────┴──────────┐
                         │                     │
                         ▼                     ▼
                ┌─────────────────┐   ┌─────────────────┐
                │ CatalogoFilmes  │   │ RatingsCatalog  │
                │     :8081       │   │     :8082       │
                └────────┬────────┘   └────────┬────────┘
                         │                     │
                         ▼                     ▼
                        MySQL              MongoDB
                         
                         ▲
                         │
                ┌────────┴────────┐
                │  Eureka Server  │
                │      :8761      │
                └─────────────────┘
```
## Como executar

O projeto é composto por quatro aplicações Spring Boot:

1. Eureka Server
2. Config Server
3. Gateway
4. CatalogoFilmes
5. RatingsCatalog

Recomenda-se iniciar as aplicações nesta ordem.

## API Gateway

O Gateway está disponível em:

`http://localhost:8080`

As requisições são encaminhadas para os microsserviços utilizando o nome do serviço registrado no Eureka.

Principais rotas:

```
http://localhost:8080/catalogofilmes/filmes
http://localhost:8080/ratingscatalog/ratings
```

## Exemplos de Requisições

### Filmes

Cadastrar filme

```
POST http://localhost:8080/catalogofilmes/filmes
Content-Type: application/json

{
    "title": "Interestelar",
    "description": "Um grupo de astronautas viaja pelo espaço em busca de um novo lar para a humanidade.",
    "year": 2014,
    "genre": "Ficção científica"
}
```

Listar filmes

`GET http://localhost:8080/catalogofilmes/filmes`

Buscar filme por ID

`GET http://localhost:8080/catalogofilmes/filmes/1`

Atualizar filme

```
PUT http://localhost:8080/catalogofilmes/filmes
Content-Type: application/json

{
    "id": 1,
    "title": "Interestelar",
    "description": "Uma equipe de astronautas explora um buraco de minhoca em busca de um novo planeta habitável.",
    "year": 2014,
    "genre": "Ficção científica"
}
```

Excluir filme

`DELETE http://localhost:8080/catalogofilmes/filmes/1`

## Avaliações

Cadastrar avaliação

```
POST http://localhost:8080/ratingscatalog/ratings
Content-Type: application/json

{
    "userId": 1,
    "movieId": 1,
    "rating": 9,
    "comment": "Excelente filme!"
}
```

Buscar avaliações de um usuário

`GET http://localhost:8080/ratingscatalog/ratings/user/1`

Buscar avaliações de um filme

`GET http://localhost:8080/ratingscatalog/ratings/movie/1`

Consultar média de avaliações de um filme

`GET http://localhost:8080/ratingscatalog/ratings/movie/1/average`

Consultar avaliações de um filme ordenadas por nota

`GET http://localhost:8080/ratingscatalog/ratings/movie/1/ordered`

Buscar avaliação por ID

`GET http://localhost:8080/ratingscatalog/ratings/{id}`

Atualizar avaliação

```
PUT http://localhost:8080/ratingscatalog/ratings
Content-Type: application/json

{
    "id": "ID_DA_AVALIACAO",
    "userId": 1,
    "movieId": 1,
    "rating": 10,
    "comment": "Excelente filme, recomendo!"
}
```

Excluir avaliação

`DELETE http://localhost:8080/ratingscatalog/ratings/{id}`

## Integração entre os microsserviços

O `CatalogoFilmes` utiliza **OpenFeign** para consultar o `RatingsCatalog`, permitindo acessar as avaliações de um filme 
diretamente pela API de filmes.

```
GET http://localhost:8080/catalogofilmes/filmes/1/ratings
GET http://localhost:8080/catalogofilmes/filmes/1/ratings/average
GET http://localhost:8080/catalogofilmes/filmes/1/ratings/ordered
```