-- 1. DONO DA MÁQUINA
CREATE TABLE IF NOT EXISTS donoDeMaquina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    usoParaVendas BOOLEAN DEFAULT FALSE
);

-- 2. CAFETEIRA
CREATE TABLE IF NOT EXISTS cafeteira (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_dono INT,
    qtdAgua_ml INT NOT NULL,
    qtdGraos_g INT NOT NULL,
    status VARCHAR(20) DEFAULT 'pronta',
    FOREIGN KEY (id_dono) REFERENCES donoDeMaquina(id)
);

-- 3. CARDÁPIO DE CAFÉS
CREATE TABLE IF NOT EXISTS cafes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cafe VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    agua_necessaria_ml INT NOT NULL,
    graos_necessarios_g INT NOT NULL
);

-- 4. COMPRADOR / CLIENTE
CREATE TABLE IF NOT EXISTS comprador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    saldo_credito DECIMAL(10,2) DEFAULT 0.00
);

-- 5. HISTÓRICO DE VENDAS
CREATE TABLE IF NOT EXISTS pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cafeteira INT,
    id_cafe INT,
    id_comprador INT,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cafeteira) REFERENCES cafeteira(id),
    FOREIGN KEY (id_cafe) REFERENCES cafes(id),
    FOREIGN KEY (id_comprador) REFERENCES comprador(id)
);

-- Insere os cafés iniciais no cardápio
INSERT INTO cafes (nome_cafe, preco, agua_necessaria_ml, graos_necessarios_g) VALUES
('Expresso Tradicional', 6.50, 50, 10),
('Cappuccino Cremoso', 9.90, 100, 15),
('Latte Macchiato', 11.50, 150, 12);