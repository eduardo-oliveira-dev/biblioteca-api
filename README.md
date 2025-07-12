# 📚 API de Biblioteca

Este é um projeto desenvolvido em Spring Boot para gerenciamento completo de uma biblioteca digital, incluindo livros, autores, usuários e perfis. A aplicação oferece uma API RESTful robusta com operações CRUD para todas as entidades do sistema.

## 🚀 Funcionalidades

### 📖 Livros
- Listar todos os livros
- Buscar livro por ID
- Cadastrar um novo livro
- Atualizar parcialmente um livro (PATCH)
- Excluir um livro

### ✍️ Autores
- Listar todos os autores
- Cadastrar um novo autor
- Relacionamento many-to-many com livros

### 👤 Usuários
- Listar todos os usuários
- Cadastrar um novo usuário
- Relacionamento one-to-many com livros
- Relacionamento one-to-one com perfil

### 🎭 Perfis
- Listar todos os perfis
- Cadastrar um novo perfil
- Relacionamento one-to-one com usuário

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Web** - Para criação da API REST
- **Spring Data JPA** - Para persistência de dados
- **MySQL 8.0** - Banco de dados relacional
- **Lombok** - Para redução de código boilerplate
- **Maven** - Para gerenciamento de dependências
- **Docker & Docker Compose** - Para containerização

## 🏗️ Arquitetura do Projeto

O projeto segue a arquitetura em camadas:

```
├── controller/     # Controladores REST
├── service/        # Lógica de negócio
├── repository/     # Acesso a dados
├── entity/         # Entidades JPA
└── dto/           # Objetos de transferência de dados
```

## 📊 Modelo de Dados

### Entidades e Relacionamentos

- **Livro ↔ Autor**: Many-to-Many (um livro pode ter vários autores, um autor pode ter vários livros)
- **Usuario ↔ Livro**: One-to-Many (um usuário pode ter vários livros)
- **Usuario ↔ Perfil**: One-to-One (um usuário tem um perfil)

### Gêneros Disponíveis

- FICCAO, NAO_FICCAO, ROMANCE, SUSPENSE, FANTASIA
- BIOGRAFIA, HISTORIA, CIENCIA, TECNOLOGIA, INFANTIL

## 🔗 Endpoints da API

### 📖 Livros
```http
GET    /livros         # Lista todos os livros
GET    /livros/{id}    # Busca um livro pelo ID
POST   /livros         # Cadastra um novo livro
PATCH  /livros/{id}    # Atualiza parcialmente um livro
DELETE /livros/{id}    # Exclui um livro pelo ID
```

### ✍️ Autores
```http
GET    /autores        # Lista todos os autores
GET    /autores/{id}   # Busca um autor pelo ID
POST   /autores        # Cadastra um novo autor
PATCH  /autores/{id}   # Atualiza parcialmente um autor
DELETE /autores/{id}   # Exclui um autor pelo ID
```

### 👤 Usuários
```http
GET    /usuarios       # Lista todos os usuários
GET    /usuarios/{id}  # Busca um usuário pelo ID
POST   /usuarios       # Cadastra um novo usuário
PATCH  /usuarios/{id}  # Atualiza parcialmente um usuário
DELETE /usuarios/{id}  # Exclui um usuário pelo ID
```

### 🎭 Perfis
```http
GET    /perfis         # Lista todos os perfis
GET    /perfis/{id}    # Busca um perfil pelo ID
POST   /perfis         # Cadastra um novo perfil
PATCH  /perfis/{id}    # Atualiza parcialmente um perfil
DELETE /perfis/{id}    # Exclui um perfil pelo ID
```

## 🚀 Como Executar o Projeto

### 1. Clonando o Repositório

```bash
# Clone o repositório
git clone https://github.com/eduardo-oliveira-dev/biblioteca-api.git

# Navegue até o diretório
cd biblioteca-api
```

### 2. 🐳 Executando com Docker (Recomendado)

#### Pré-requisitos
- Docker
- Docker Compose

#### Executar o projeto

```bash
# Executar com Docker Compose
docker-compose up

# Para executar em background
docker-compose up -d

# A API estará disponível em:
# http://localhost:8080
```

#### Parar o projeto

```bash
# Parar os containers
docker-compose down

# Parar e remover volumes (limpar banco de dados)
docker-compose down -v
```

### 3. ⚙️ Configuração Manual

#### Pré-requisitos
- Java 21
- Maven
- MySQL 8.0

#### Configuração do Banco de Dados

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

#### Executar o projeto

```bash
# Compilar e executar
./mvnw spring-boot:run

# Ou com Maven instalado
mvn spring-boot:run

# A API estará disponível em:
# http://localhost:8080
```

## 📝 Ordem Recomendada para Testes

Para evitar erros de referência, siga esta ordem:
1. Criar Autores
2. Criar Usuários
3. Criar Livros (referenciando autores e usuários existentes)
4. Criar Perfis (referenciando usuários existentes)

## 📄 Exemplos de Requisições

### Criar um Autor

```http
POST /autores
Content-Type: application/json

{
  "nome": "Machado de Assis",
  "nacionalidade": "Brasileira"
}
```

**Resposta de sucesso**: 201 Created

### Criar um Usuário com Perfil

```http
POST /usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "perfil": {
    "bio": "Apaixonado por literatura clássica",
    "avatarUrl": "https://exemplo.com/avatar.jpg"
  }
}
```

**Resposta de sucesso**: 201 Created

### Criar um Livro

```http
POST /livros
Content-Type: application/json

{
  "titulo": "Dom Casmurro",
  "isbn": "9788525406958",
  "resumo": "Romance clássico da literatura brasileira",
  "dataPublicacao": "1899-01-01",
  "numeroPaginas": 256,
  "editora": "Ática",
  "genero": "FICCAO",
  "autores": [
    {
      "id": 1
    }
  ],
  "usuario": {
    "id": 1
  }
}
```

**Resposta de sucesso**: 201 Created

### Listar Livros

```http
GET /livros
```

**Resposta de sucesso**: 200 OK

## 🎓 Objetivos de Aprendizado

Este projeto foi desenvolvido para praticar:

- Desenvolvimento de APIs REST com Spring Boot
- Relacionamentos JPA complexos (OneToOne, OneToMany, ManyToMany)
- Arquitetura em camadas (Controller, Service, Repository)
- Operações CRUD completas
- Atualização parcial com método PATCH
- Containerização com Docker
- Boas práticas de desenvolvimento Java

## 📌 Observações Técnicas

- **Padrão DTO**: Uso de Data Transfer Objects (DTOs) para evitar referências circulares e controlar a serialização JSON
- **Relacionamentos**: Implementação de relacionamentos bidirecionais com mapeamento adequado entre entidades e DTOs
- **Atualização Parcial**: Método PATCH implementado para atualizar apenas campos específicos
- **Containerização**: Configuração completa com Docker Compose incluindo banco de dados
- **Banco de Dados**: MySQL com configuração automática de tabelas

## 👨‍💻 Autor

**Eduardo Oliveira**

⭐ Se este projeto foi útil para você, considere dar uma estrela!