CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE produtos (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(50),
    quantidade_estoque INT NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserts mockados
INSERT INTO produtos (id, nome, descricao, preco, categoria, quantidade_estoque, status)
VALUES
(uuid_generate_v4(), 'Notebook Dell XPS 13', 'Notebook com Intel i7, 16GB RAM, SSD 512GB', 8999.90, 'INFORMATICA', 10, 'ATIVO'),
(uuid_generate_v4(), 'Smartphone Samsung S24 Ultra', 'Tela AMOLED 6.8", 12GB RAM, 512GB', 7599.00, 'TELEFONIA', 20, 'ATIVO'),
(uuid_generate_v4(), 'Cadeira Gamer ThunderX3', 'Cadeira com suporte lombar e apoio de braço 4D', 1499.90, 'MÓVEIS', 15, 'ATIVO');