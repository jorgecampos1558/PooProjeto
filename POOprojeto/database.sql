DROP DATABASE IF EXISTS scoutpro;
CREATE DATABASE scoutpro;
USE scoutpro;

-- ====================================================================
-- 1. CRIAÇÃO DAS TABELAS
-- ====================================================================

CREATE TABLE jogadores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    idade INT,
    posicao VARCHAR(10),
    numero INT,
    jogos INT,
    jogos_titular INT,
    minutos_jogados INT
);

CREATE TABLE atacante_stats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jogador_id INT,
    gols INT,
    assistencias INT,
    finalizacoes INT,
    finalizacoes_no_gol INT,
    dribles_certos INT,
    perdas_de_posse INT,
    FOREIGN KEY (jogador_id) REFERENCES jogadores(id)
);

CREATE TABLE meio_stats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jogador_id INT,
    assistencias INT,
    passes_certos INT,
    passes_tentados INT,
    passes_decisivos INT,
    chances_criadas INT,
    desarmes INT,
    interceptacoes INT,
    FOREIGN KEY (jogador_id) REFERENCES jogadores(id)
);

CREATE TABLE defensor_stats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jogador_id INT,
    desarmes INT,
    interceptacoes INT,
    cortes INT,
    duelos_ganhos INT,
    duelos_tentados INT,
    faltas INT,
    FOREIGN KEY (jogador_id) REFERENCES jogadores(id)
);

CREATE TABLE goleiro_stats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jogador_id INT,
    defesas INT,
    defesas_dificeis INT,
    gols_sofridos INT,
    xg_contra DOUBLE,
    clean_sheets INT,
    penaltis_defendidos INT,
    FOREIGN KEY (jogador_id) REFERENCES jogadores(id)
);

-- Restrição para garantir consistência de numeração por jogador
ALTER TABLE jogadores ADD UNIQUE (nome, numero);


-- ====================================================================
-- 2. POPULAR A TABELA 'jogadores' (IDs 1 a 66)
-- ====================================================================

INSERT INTO jogadores (id, nome, idade, posicao, numero, jogos, jogos_titular, minutos_jogados) VALUES
-- === CORINTHIANS (IDs 1 a 22) ===
-- Goleiros
(1, 'Hugo Souza', 26, 'GK', 22, 35, 35, 3150),
(2, 'Matheus Donelli', 23, 'GK', 32, 3, 3, 270),
(3, 'Felipe Longo', 21, 'GK', 12, 1, 0, 45),
-- Defensores
(4, 'André Ramalho', 33, 'CB', 5, 30, 30, 2700),
(5, 'Gustavo Henrique', 32, 'CB', 13, 18, 15, 1400),
(6, 'Félix Torres', 29, 'CB', 3, 25, 24, 2180),
(7, 'Cacá', 26, 'CB', 25, 22, 20, 1850),
(8, 'Matheus Bidu', 26, 'CB', 21, 24, 20, 1800),
(9, 'Fagner', 36, 'CB', 23, 20, 18, 1600),
(10, 'Matheuzinho', 25, 'CB', 2, 26, 22, 2000),
(11, 'Hugo', 28, 'CB', 46, 15, 12, 1100),
-- Meio-Campistas
(12, 'Rodrigo Garro', 27, 'CM', 10, 34, 33, 2900),
(13, 'Breno Bidon', 20, 'CM', 27, 28, 22, 1950),
(14, 'Alex Santana', 30, 'CM', 14, 15, 12, 1050),
(15, 'Raniele', 29, 'CM', 14, 28, 25, 2200),
(16, 'Charles', 30, 'CM', 8, 22, 14, 1300),
(17, 'José Martínez', 31, 'CM', 70, 24, 20, 1750),
(18, 'Igor Coronado', 33, 'CM', 77, 26, 12, 1250),
-- Atacantes
(19, 'Yuri Alberto', 24, 'ST', 9, 32, 30, 2600),
(20, 'Memphis Depay', 32, 'ST', 94, 24, 20, 1800),
(21, 'Ángel Romero', 33, 'ST', 11, 30, 18, 1700),
(22, 'Talles Magno', 23, 'ST', 19, 22, 15, 1350),

-- === BAHIA (IDs 23 a 44) ===
-- Goleiros
(23, 'Marcos Felipe', 30, 'GK', 22, 36, 36, 3240),
(24, 'Adriel', 25, 'GK', 1, 2, 2, 180),
(25, 'Danilo Fernandes', 38, 'GK', 12, 1, 0, 30),
-- Defensores
(26, 'Gabriel Xavier', 24, 'CB', 3, 33, 33, 2970),
(27, 'Kanu', 29, 'CB', 4, 31, 31, 2790),
(28, 'Victor Cuesta', 37, 'CB', 18, 15, 12, 1150),
(29, 'Santiago Arias', 34, 'CB', 13, 28, 26, 2300),
(30, 'Gilberto', 33, 'CB', 2, 18, 14, 1300),
(31, 'Luciano Juba', 26, 'CB', 46, 34, 32, 2850),
(32, 'Iago Borduchi', 29, 'CB', 15, 16, 10, 980),
(33, 'David Duarte', 31, 'CB', 33, 8, 5, 520),
-- Meio-Campistas
(34, 'Everton Ribeiro', 36, 'CM', 10, 31, 28, 2300),
(35, 'Cauly', 30, 'CM', 8, 34, 32, 2800),
(36, 'Jean Lucas', 27, 'CM', 6, 33, 33, 2900),
(37, 'Caio Alexandre', 27, 'CM', 5, 32, 31, 2750),
(38, 'Rezende', 31, 'CM', 12, 22, 12, 1200),
(39, 'Thaciano', 30, 'CM', 16, 33, 30, 2600),
(40, 'Carlos de Pena', 34, 'CM', 14, 18, 6, 750),
-- Atacantes
(41, 'Lucho Rodríguez', 22, 'ST', 19, 33, 26, 2200),
(42, 'Everaldo', 34, 'ST', 9, 30, 25, 2100),
(43, 'Biel', 25, 'ST', 11, 28, 12, 1300),
(44, 'Ademir', 31, 'ST', 7, 26, 10, 1100),

-- === VITÓRIA (IDs 45 a 66) ===
-- Goleiros
(45, 'Lucas Arcanjo', 27, 'GK', 12, 37, 37, 3330),
(46, 'Muriel', 39, 'GK', 1, 1, 1, 90),
(47, 'Alexandre Fintelman', 24, 'GK', 22, 1, 0, 15),
-- Defensores
(48, 'Wagner Leonardo', 26, 'CB', 4, 36, 36, 3240),
(49, 'Neris', 34, 'CB', 27, 28, 27, 2400),
(50, 'Camutanga', 32, 'CB', 13, 14, 12, 1050),
(51, 'Bruno Uvini', 35, 'CB', 15, 12, 8, 800),
(52, 'Willean Lepo', 29, 'CB', 2, 32, 30, 2650),
(53, 'Raúl Cáceres', 34, 'CB', 29, 15, 10, 950),
(54, 'Lucas Esteves', 25, 'CB', 6, 34, 33, 2950),
(55, 'Patric Calmon', 31, 'CB', 16, 16, 8, 850),
-- Meio-Campistas
(56, 'Matheusinho', 28, 'CM', 10, 35, 34, 2950),
(57, 'Willian Oliveira', 32, 'CM', 5, 33, 32, 2800),
(58, 'Luan Santos', 26, 'CM', 8, 26, 20, 1800),
(59, 'Leo Naldi', 24, 'CM', 18, 22, 12, 1150),
(60, 'Jean Mota', 32, 'CM', 7, 20, 10, 1000),
(61, 'Machado', 30, 'CM', 28, 24, 18, 1550),
(62, 'Gabriel Santiago', 26, 'CM', 17, 12, 4, 450),
-- Atacantes
(63, 'Alerrandro', 25, 'ST', 9, 34, 31, 2650),
(64, 'Janderson', 26, 'ST', 11, 28, 15, 1400),
(65, 'Osvaldo', 39, 'ST', 10, 25, 12, 1100),
(66, 'Zé Hugo', 26, 'ST', 19, 24, 8, 850);


-- ====================================================================
-- 3. POPULAR TABELAS DE ESTATÍSTICAS (Mapeamento Correto dos IDs)
-- ====================================================================

-- ESTATÍSTICAS DE ATACANTES
INSERT INTO atacante_stats (jogador_id, gols, assistencias, finalizacoes, finalizacoes_no_gol, dribles_certos, perdas_de_posse) VALUES
-- Corinthians (19 a 22)
(19, 14, 3, 85, 42, 28, 35),
(20, 11, 6, 70, 38, 45, 40),
(21, 7, 4, 48, 22, 18, 25),
(22, 6, 5, 40, 19, 32, 28),
-- Bahia (41 a 44)
(41, 10, 4, 72, 31, 45, 32),
(42, 9, 2, 58, 26, 12, 28),
(43, 5, 6, 38, 18, 35, 24),
(44, 4, 3, 32, 14, 28, 20),
-- Vitória (63 a 66)
(63, 12, 3, 78, 35, 20, 26),
(64, 5, 2, 35, 15, 28, 22),
(65, 4, 5, 28, 12, 18, 18),
(66, 3, 2, 20, 9, 22, 15);


-- ESTATÍSTICAS DE MEIO-CAMPISTAS
INSERT INTO meio_stats (jogador_id, assistencias, passes_certos, passes_tentados, passes_decisivos, chances_criadas, desarmes, interceptacoes) VALUES
-- Corinthians (12 a 18)
(12, 9, 1450, 1780, 78, 15, 40, 25),
(13, 3, 920, 1080, 32, 6, 45, 30),
(14, 1, 420, 510, 12, 3, 28, 15),
(15, 1, 880, 1020, 10, 2, 65, 48),
(16, 2, 550, 660, 18, 4, 32, 20),
(17, 0, 710, 820, 8, 1, 58, 38),
(18, 5, 580, 700, 45, 8, 15, 10),
-- Bahia (34 a 40)
(34, 7, 1210, 1420, 58, 11, 22, 18),
(35, 8, 1350, 1600, 68, 13, 25, 15),
(36, 4, 1420, 1650, 38, 8, 55, 32),
(37, 2, 1680, 1880, 25, 5, 48, 35),
(38, 0, 480, 550, 6, 1, 42, 28),
(39, 5, 850, 1020, 42, 9, 38, 22),
(40, 2, 310, 380, 15, 3, 14, 8),
-- Vitória (56 a 62)
(56, 6, 1150, 1400, 62, 12, 35, 28),
(57, 1, 950, 1120, 15, 3, 72, 45),
(58, 2, 620, 720, 20, 4, 48, 30),
(59, 1, 380, 450, 12, 2, 30, 18),
(60, 3, 410, 500, 28, 5, 15, 10),
(61, 2, 580, 690, 24, 4, 28, 16),
(62, 1, 150, 190, 8, 2, 10, 5);


-- ESTATÍSTICAS DE DEFENSORES (Mapeamento Completo de 24 jogadores com duelos_tentados)
INSERT INTO defensor_stats (jogador_id, desarmes, interceptacoes, cortes, duelos_ganhos, duelos_tentados, faltas) VALUES
-- Corinthians (4 a 11)
(4, 48, 32, 85, 110, 165, 28),
(5, 24, 18, 55, 62, 95, 19),
(6, 35, 28, 72, 88, 130, 22),
(7, 30, 22, 60, 75, 115, 25),
(8, 42, 20, 35, 68, 110, 18),
(9, 38, 15, 28, 55, 95, 24),
(10, 45, 24, 38, 72, 120, 20),
(11, 25, 14, 25, 40, 70, 12),
-- Bahia (26 a 33)
(26, 42, 38, 95, 125, 180, 22),
(27, 38, 32, 88, 112, 170, 28),
(28, 18, 15, 42, 45, 70, 15),
(29, 48, 25, 45, 80, 130, 24),
(30, 28, 14, 22, 48, 80, 18),
(31, 52, 30, 40, 92, 150, 20),
(32, 22, 12, 18, 35, 60, 10),
(33, 10, 8, 24, 20, 32, 6),
-- Vitória (48 a 55)
(48, 55, 45, 120, 140, 210, 35),
(49, 34, 28, 78, 82, 135, 26),
(50, 18, 15, 35, 38, 65, 14),
(51, 12, 10, 28, 25, 42, 10),
(52, 50, 28, 38, 88, 145, 24),
(53, 18, 10, 15, 30, 50, 12),
(54, 48, 25, 32, 85, 140, 22),
(55, 22, 12, 14, 32, 55, 15);


-- ESTATÍSTICAS DE GOLEIROS
INSERT INTO goleiro_stats (jogador_id, defesas, defesas_dificeis, gols_sofridos, xg_contra, clean_sheets, penaltis_defendidos) VALUES
-- Corinthians (1 a 3)
(1, 112, 34, 38, 44.5, 11, 3),
(2, 8, 2, 4, 3.8, 1, 0),
(3, 2, 0, 1, 0.9, 0, 0),
-- Bahia (23 a 25)
(23, 105, 28, 42, 41.2, 10, 1),
(24, 6, 1, 3, 2.5, 0, 0),
(25, 1, 0, 1, 0.5, 0, 0),
-- Vitória (45 a 47)
(45, 128, 38, 48, 51.3, 9, 2),
(46, 3, 1, 2, 1.8, 0, 0),
(47, 1, 0, 0, 0.2, 0, 0);

-- ====================================================================
-- 4. CONSULTAS DE TESTE (Corrigidas para usar as siglas do banco: ST, CM, CB, GK)
-- ====================================================================

-- 4.1. Listar Atacantes
SELECT 
    j.nome,
    j.idade,
    j.numero,
    j.jogos,
    j.jogos_titular,
    j.minutos_jogados,
    a.gols,
    a.assistencias,
    a.finalizacoes,
    a.finalizacoes_no_gol,
    a.dribles_certos,
    a.perdas_de_posse
FROM jogadores j
JOIN atacante_stats a ON j.id = a.jogador_id
WHERE j.posicao = 'ST';

-- 4.2. Listar Meio-Campistas com métricas avançadas
SELECT
    j.nome,
    j.idade,
    j.numero,
    j.jogos,
    j.jogos_titular,
    j.minutos_jogados,
    m.assistencias,
    m.passes_certos,
    m.passes_tentados,
    m.passes_decisivos,
    m.chances_criadas,
    m.desarmes,
    m.interceptacoes,
    ROUND((m.passes_certos * 100.0) / m.passes_tentados, 1) AS precisao_passe,
    ROUND((m.assistencias * 90.0) / j.minutos_jogados, 2) AS assistencias_por90,
    ROUND((m.assistencias * 0.4) + (m.passes_decisivos * 0.15) + (m.chances_criadas * 0.10), 1) AS xA
FROM jogadores j
INNER JOIN meio_stats m ON j.id = m.jogador_id
WHERE j.posicao = 'CM';

-- 4.3. Listar Defensores
SELECT 
    j.nome,
    j.idade,
    j.numero,
    j.jogos,
    j.jogos_titular,
    j.minutos_jogados,
    d.desarmes,
    d.interceptacoes,
    d.cortes,
    d.duelos_ganhos,
    d.duelos_tentados,
    d.faltas
FROM jogadores j
JOIN defensor_stats d ON j.id = d.jogador_id
WHERE j.posicao = 'CB';

-- 4.4. Listar Goleiros
SELECT 
    j.nome,
    j.idade,
    j.numero,
    j.jogos,
    j.jogos_titular,
    j.minutos_jogados,
    g.defesas,
    g.defesas_dificeis,
    g.gols_sofridos,
    g.clean_sheets,
    g.penaltis_defendidos
FROM jogadores j
JOIN goleiro_stats g ON j.id = g.jogador_id
WHERE j.posicao = 'GK';