
-- Base de datos 'botiga'
CREATE DATABASE IF NOT EXISTS botiga;
USE botiga;

-- Tabla 'categories'
CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Tabla 'productes'
CREATE TABLE IF NOT EXISTS productes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    preu DECIMAL(10, 2) NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categories(id)
);

-- Tabla 'clients'
CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Tabla 'comandes'
CREATE TABLE IF NOT EXISTS comandes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES clients(id)
);

-- Tabla 'detalls_comanda'
CREATE TABLE IF NOT EXISTS detalls_comanda (
    id_comanda INT,
    id_producte INT,
    PRIMARY KEY (id_comanda, id_producte),
    FOREIGN KEY (id_comanda) REFERENCES comandes(id),
    FOREIGN KEY (id_producte) REFERENCES productes(id)
);
