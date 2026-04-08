CREATE DATABASE safeher;

-- usar banco (PostgreSQL)
\c safeher;

CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100),
    data DATE
);

CREATE TABLE Empresa (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    indice DECIMAL(5,2)
);

CREATE TABLE Avaliacao (
    id INT PRIMARY KEY,
    comentario TEXT,
    nota INT,
    Usuario_id INT,
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE Denuncia (
    id INT PRIMARY KEY,
    descricao TEXT,
    data DATE,
    anonima BOOLEAN,
    Usuario_id INT,
    Empresa_id INT,
    FOREIGN KEY (Usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (Empresa_id) REFERENCES Empresa(id)
);