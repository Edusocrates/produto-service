# 📦 Produto Service

Microsserviço responsável pelo **gerenciamento de produtos**, incluindo criação, atualização, busca e emissão de eventos para outros serviços do ecossistema (como Estoque e Pedido).

---

## 📚 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Clean Architecture
- Docker
- RabbitMQ
- PostgreSQL

---

## 📁 Estrutura do Projeto
```bash
produto_service
├── api
│   └── controllers, requests, responses
├── aplicacao
│   └── usecases (CreateProduct, UpdatePrice, etc)
├── dominio
│   └── entities, gateways
├── infraestrutura
│   ├── config (Rabbit)
│   ├── messaging (producers, listeners)
│   ├── persistence (repositories, entities, adapters)

```


## ⚙️ Endpoints Disponíveis

| Método | Endpoint            | Descrição                  |
|--------|---------------------|----------------------------|
| POST   | `/produtos`         | Criação de novo produto    |
| PATCH  | `/produtos/{id}`    | Atualiza preço do produto  |
| GET    | `/produtos/{id}`    | Consulta produto por ID    |
| GET    | `/produtos`         | Lista todos os produtos    |


---

## 🔁 Comunicação Assíncrona (RabbitMQ)

### Eventos Emitidos

- `produto.criado`: enviado para `estoque_service` ao criar produto
- `produto.preco.atualizado`: enviado para `pedido_service` ao atualizar preço

### Eventos Recebidos

- `pedido.sincronizar.produto`: recebido de `pedido_service`, responde com dados do produto

---

## 🐳 Docker

### Pré-requisitos
- Docker
- Docker Compose
- PostgreSQL
- RabbitMQ
- Spring Boot
- Java 17
- Maven

### Comando para subir o projeto:
```bash
docker-compose up --build
```
## Acesso ao RabbitMQ
- Host: `localhost`
- Porta: `15672`
- Usuário: `guest`
- Senha: `guest`
- Vá para `http://localhost:15672` para acessar a interface do RabbitMQ.
- Crie uma fila chamada `produto` para receber mensagens.
- Crie uma exchange chamada `produto` para enviar mensagens.
- Crie uma binding entre a fila e a exchange.
- Crie uma rota chamada `produto.criado` para receber mensagens da exchange.
- Crie uma rota chamada `produto.preco.atualizado` para receber mensagens da exchange.
- Crie uma rota chamada `pedido.sincronizar.produto` para receber mensagens da exchange.

## Acesso ao banco de dados (PostgreSQL)
- Host: `localhost`
- Porta: `5432`
- Usuário: `postgres`
- Senha: `postgres`
- Banco: `produto_db`
- Driver: `PostgreSQL`
- JDBC URL: `jdbc:postgresql://localhost:5432/produto_db`
- JDBC Driver: `org.postgresql.Driver`
- Dialect: `org.hibernate.dialect.PostgreSQLDialect`

---
## 🧪 Testes
### Testes Unitários
- Testes de unidade para cada camada do projeto ( aplicação, infraestrutura e API).
- Utiliza JUnit 5 e Mockito para simular dependências.


### 👨‍💻 Requisitos de Negócio
- O produto deve ter um nome, descrição e preço.
- O preço deve ser maior que zero.
- O nome do produto deve ser único.
- O produto deve ser criado com status "ATIVO".
- O produto deve ser atualizado com status "ATUALIZADO".
- O produto deve ser excluído com status "EXCLUIDO".
- O produto deve ser consultado por ID ou nome.
- O produto deve ser listado por nome ou preço.
- O produto deve ser enviado para o RabbitMQ ao ser criado ou atualizado.
- O produto deve ser recebido do RabbitMQ ao ser consultado.


## ✍️ Autor
- Eduardo Sócrates Caria
- GitHub: https://github.com/Edusocrates
- RM: 358568
- Turma: 6ADJT
- Grupo 15

