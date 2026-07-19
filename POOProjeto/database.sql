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
    minutos_jogados INT,
    time VARCHAR(50)
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
    xG DOUBLE,
    grandes_chances_perdidas INT,
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
    bolas_recuperadas INT,
    perdas_de_posse INT,
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
    duelosaereos_ganhos INT,
    duelosaereos_tentados INT,
    bolas_recuperadas INT,
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

ALTER TABLE jogadores ADD UNIQUE (nome, numero);


-- ====================================================================
-- 2. POPULAR A TABELA 'jogadores' (Temporada 2025 com a coluna Time)
-- ====================================================================

INSERT INTO jogadores (id, nome, idade, posicao, numero, jogos, jogos_titular, minutos_jogados, time) VALUES
-- === CORINTHIANS (IDs 1 a 22) ===
(1, 'Hugo Souza', 26, 'GK', 22, 38, 38, 3420, 'Corinthians'),
(2, 'Matheus Donelli', 23, 'GK', 32, 2, 2, 180, 'Corinthians'),
(3, 'Felipe Longo', 21, 'GK', 12, 1, 0, 15, 'Corinthians'),
(4, 'André Ramalho', 33, 'CB', 5, 34, 34, 3060, 'Corinthians'),
(5, 'Gustavo Henrique', 32, 'CB', 13, 20, 16, 1520, 'Corinthians'),
(6, 'Félix Torres', 29, 'CB', 3, 28, 27, 2410, 'Corinthians'),
(7, 'Cacá', 26, 'CB', 25, 24, 22, 1980, 'Corinthians'),
(8, 'Matheus Bidu', 26, 'CB', 21, 26, 23, 2010, 'Corinthians'),
(9, 'Fagner', 36, 'CB', 23, 18, 15, 1380, 'Corinthians'),
(10, 'Matheuzinho', 25, 'CB', 2, 29, 25, 2210, 'Corinthians'),
(11, 'Hugo', 28, 'CB', 46, 14, 10, 960, 'Corinthians'),
(12, 'Rodrigo Garro', 27, 'CM', 10, 36, 35, 3110, 'Corinthians'),
(13, 'Breno Bidon', 20, 'CM', 27, 30, 24, 2100, 'Corinthians'),
(14, 'Alex Santana', 30, 'CM', 14, 18, 11, 1090, 'Corinthians'),
(15, 'Raniele', 29, 'CM', 14, 26, 22, 1950, 'Corinthians'),
(16, 'Charles', 30, 'CM', 8, 24, 12, 1150, 'Corinthians'),
(17, 'José Martínez', 31, 'CM', 70, 28, 24, 2080, 'Corinthians'),
(18, 'Igor Coronado', 33, 'CM', 77, 29, 14, 1420, 'Corinthians'),
(19, 'Yuri Alberto', 24, 'ST', 9, 35, 33, 2870, 'Corinthians'),
(20, 'Memphis Depay', 32, 'ST', 94, 28, 25, 2150, 'Corinthians'),
(21, 'Ángel Romero', 33, 'ST', 11, 32, 16, 1620, 'Corinthians'),
(22, 'Talles Magno', 23, 'ST', 19, 25, 14, 1310, 'Corinthians'),

-- === BAHIA (IDs 23 a 44) ===
(23, 'Marcos Felipe', 30, 'GK', 22, 37, 37, 3330, 'Bahia'),
(24, 'Adriel', 25, 'GK', 1, 1, 1, 90, 'Bahia'),
(25, 'Danilo Fernandes', 38, 'GK', 12, 1, 0, 60, 'Bahia'),
(26, 'Gabriel Xavier', 24, 'CB', 3, 35, 35, 3150, 'Bahia'),
(27, 'Kanu', 29, 'CB', 4, 32, 32, 2880, 'Bahia'),
(28, 'Victor Cuesta', 37, 'CB', 18, 12, 9, 890, 'Bahia'),
(29, 'Santiago Arias', 34, 'CB', 13, 29, 28, 2450, 'Bahia'),
(30, 'Gilberto', 33, 'CB', 2, 16, 12, 1120, 'Bahia'),
(31, 'Luciano Juba', 26, 'CB', 46, 36, 35, 3090, 'Bahia'),
(32, 'Iago Borduchi', 29, 'CB', 15, 18, 9, 920, 'Bahia'),
(33, 'David Duarte', 31, 'CB', 33, 6, 4, 410, 'Bahia'),
(34, 'Everton Ribeiro', 36, 'CM', 10, 33, 30, 2480, 'Bahia'),
(35, 'Cauly', 30, 'CM', 8, 35, 33, 2890, 'Bahia'),
(36, 'Jean Lucas', 27, 'CM', 6, 34, 34, 2990, 'Bahia'),
(37, 'Caio Alexandre', 27, 'CM', 5, 33, 32, 2810, 'Bahia'),
(38, 'Rezende', 31, 'CM', 12, 19, 10, 1020, 'Bahia'),
(39, 'Thaciano', 30, 'CM', 16, 34, 31, 2670, 'Bahia'),
(40, 'Carlos de Pena', 34, 'CM', 14, 20, 5, 710, 'Bahia'),
(41, 'Lucho Rodríguez', 22, 'ST', 19, 35, 29, 2460, 'Bahia'),
(42, 'Everaldo', 34, 'ST', 9, 31, 24, 2050, 'Bahia'),
(43, 'Biel', 25, 'ST', 11, 26, 14, 1280, 'Bahia'),
(44, 'Ademir', 31, 'ST', 7, 28, 8, 990, 'Bahia'),

-- === VITÓRIA (IDs 45 a 66) ===
(45, 'Lucas Arcanjo', 27, 'GK', 12, 38, 38, 3420, 'Vitória'),
(46, 'Muriel', 39, 'GK', 1, 1, 0, 20, 'Vitória'),
(47, 'Alexandre Fintelman', 24, 'GK', 22, 1, 0, 10, 'Vitória'),
(48, 'Wagner Leonardo', 26, 'CB', 4, 37, 37, 3330, 'Vitória'),
(49, 'Neris', 34, 'CB', 27, 30, 29, 2550, 'Vitória'),
(50, 'Camutanga', 32, 'CB', 13, 10, 8, 750, 'Vitória'),
(51, 'Bruno Uvini', 35, 'CB', 15, 14, 9, 880, 'Vitória'),
(52, 'Willean Lepo', 29, 'CB', 2, 33, 31, 2720, 'Vitória'),
(53, 'Raúl Cáceres', 34, 'CB', 29, 12, 8, 790, 'Vitória'),
(54, 'Lucas Esteves', 25, 'CB', 6, 35, 34, 3010, 'Vitória'),
(55, 'Patric Calmon', 31, 'CB', 16, 14, 6, 680, 'Vitória'),
(56, 'Matheuzinho', 28, 'CM', 10, 36, 35, 3080, 'Vitória'),
(57, 'Willian Oliveira', 32, 'CM', 5, 34, 33, 2890, 'Vitória'),
(58, 'Luan Santos', 26, 'CM', 8, 25, 18, 1650, 'Vitória'),
(59, 'Leo Naldi', 24, 'CM', 18, 20, 10, 980, 'Vitória'),
(60, 'Jean Mota', 32, 'CM', 7, 18, 8, 820, 'Vitória'),
(61, 'Machado', 30, 'CM', 28, 26, 19, 1610, 'Vitória'),
(62, 'Gabriel Santiago', 26, 'CM', 17, 10, 3, 380, 'Vitória'),
(63, 'Alerrandro', 25, 'ST', 9, 35, 33, 2790, 'Vitória'),
(64, 'Janderson', 26, 'ST', 11, 30, 14, 1350, 'Vitória'),
(65, 'Osvaldo', 39, 'ST', 10, 22, 10, 920, 'Vitória'),
(66, 'Zé Hugo', 26, 'ST', 19, 25, 7, 810, 'Vitória');


-- ====================================================================
-- 3. POPULAR TABELAS DE ESTATÍSTICAS
-- ====================================================================

-- ESTATÍSTICAS DE ATACANTES
INSERT INTO atacante_stats (jogador_id, gols, assistencias, finalizacoes, finalizacoes_no_gol, dribles_certos, perdas_de_posse, xg, grandes_chances_perdidas) VALUES
(19, 16, 4, 92, 46, 30, 38, 14.2, 12),
(20, 13, 7, 78, 42, 51, 44, 11.1, 7),
(21, 6, 3, 44, 20, 15, 22, 5.4, 4),
(22, 5, 6, 42, 21, 36, 31, 5.8, 5),
(41, 12, 5, 80, 36, 48, 35, 10.3, 8),
(42, 8, 3, 54, 24, 14, 26, 7.9, 9),
(43, 6, 7, 41, 20, 38, 22, 4.9, 2),
(44, 3, 2, 29, 12, 25, 19, 3.1, 3),
(63, 14, 4, 82, 39, 22, 29, 12.1, 10),
(64, 4, 3, 31, 13, 26, 24, 3.8, 5),
(65, 3, 4, 24, 10, 15, 16, 2.9, 1),
(66, 2, 1, 18, 8, 20, 14, 2.1, 2);

-- ESTATÍSTICAS DE MEIO-CAMPISTAS
INSERT INTO meio_stats (jogador_id, assistencias, passes_certos, passes_tentados, passes_decisivos, chances_criadas, desarmes, interceptacoes, bolas_recuperadas, perdas_de_posse) VALUES
(12, 11, 1520, 1840, 84, 18, 42, 27, 122, 52),
(13, 4, 980, 1140, 36, 8, 48, 33, 138, 31),
(14, 2, 450, 540, 15, 4, 31, 18, 66, 14),
(15, 1, 910, 1050, 12, 3, 68, 51, 192, 22),
(16, 2, 580, 690, 20, 5, 35, 22, 85, 19),
(17, 0, 740, 850, 9, 2, 61, 41, 148, 25),
(18, 6, 620, 740, 49, 10, 18, 12, 54, 38),
(34, 8, 1260, 1470, 62, 13, 24, 20, 95, 41),
(35, 9, 1390, 1640, 72, 15, 27, 17, 116, 47),
(36, 5, 1460, 1690, 41, 9, 58, 35, 166, 36),
(37, 3, 1720, 1920, 28, 6, 51, 38, 175, 24),
(38, 0, 510, 580, 8, 2, 45, 30, 101, 12),
(39, 6, 890, 1060, 45, 11, 41, 25, 126, 33),
(40, 1, 290, 350, 12, 2, 12, 6, 41, 15),
(56, 7, 1210, 1460, 67, 14, 38, 31, 135, 49),
(57, 2, 990, 1160, 18, 4, 75, 48, 196, 20),
(58, 1, 640, 750, 22, 5, 51, 32, 131, 26),
(59, 1, 400, 470, 14, 3, 32, 20, 79, 15),
(60, 2, 390, 480, 25, 4, 13, 9, 51, 22),
(61, 3, 610, 720, 27, 5, 31, 18, 106, 21),
(62, 1, 130, 170, 6, 1, 8, 4, 27, 11);

-- ESTATÍSTICAS DE DEFENSORES
INSERT INTO defensor_stats (jogador_id, desarmes, interceptacoes, cortes, duelos_ganhos, duelos_tentados, faltas, duelosaereos_ganhos, duelosaereos_tentados, bolas_recuperadas) VALUES
(4, 52, 35, 91, 118, 172, 30, 59, 84, 126),
(5, 26, 20, 59, 68, 102, 21, 41, 59, 62),
(6, 38, 30, 76, 94, 138, 24, 49, 70, 101),
(7, 33, 25, 64, 81, 122, 27, 43, 65, 88),
(8, 45, 22, 38, 72, 116, 20, 24, 43, 94),
(9, 35, 13, 25, 51, 89, 22, 10, 22, 70),
(10, 49, 27, 41, 78, 128, 22, 17, 33, 104),
(11, 23, 12, 22, 37, 65, 10, 16, 29, 43),
(26, 45, 41, 101, 132, 188, 24, 69, 100, 142),
(27, 40, 34, 92, 116, 175, 30, 61, 89, 127),
(28, 15, 12, 36, 39, 62, 12, 21, 34, 44),
(29, 51, 27, 48, 85, 136, 26, 22, 41, 111),
(30, 25, 12, 19, 43, 74, 16, 13, 26, 54),
(31, 55, 32, 44, 98, 158, 22, 27, 48, 137),
(32, 25, 14, 21, 39, 66, 12, 14, 25, 48),
(33, 8, 6, 19, 16, 27, 5, 11, 16, 20),
(48, 58, 48, 126, 148, 218, 38, 79, 115, 152),
(49, 36, 30, 82, 88, 142, 28, 45, 70, 116),
(50, 14, 11, 27, 30, 53, 11, 16, 26, 36),
(51, 15, 11, 31, 29, 48, 12, 17, 25, 39),
(52, 52, 30, 41, 92, 151, 26, 24, 49, 122),
(53, 15, 8, 13, 26, 44, 10, 10, 21, 36),
(54, 50, 27, 35, 89, 146, 24, 20, 38, 138),
(55, 19, 10, 12, 28, 49, 13, 8, 16, 31);

-- ESTATÍSTICAS DE GOLEIROS
INSERT INTO goleiro_stats (jogador_id, defesas, defesas_dificeis, gols_sofridos, xg_contra, clean_sheets, penaltis_defendidos) VALUES
(1, 121, 38, 40, 46.8, 13, 4),
(2, 6, 1, 3, 2.9, 0, 0),
(3, 1, 0, 0, 0.2, 0, 0),
(23, 108, 30, 44, 43.1, 11, 2),
(24, 3, 0, 2, 1.8, 0, 0),
(25, 2, 0, 1, 0.8, 0, 0),
(45, 132, 42, 51, 54.7, 8, 2),
(46, 1, 0, 1, 0.9, 0, 0),
(47, 0, 0, 0, 0.0, 0, 0);


-- ====================================================================
-- 4. CONSULTAS DE TESTE
-- ====================================================================

-- 4.1. Listar Atacantes (com Time)
SELECT 
    j.nome,
    j.time, -- Puxando o time aqui
    j.idade,
    j.numero,
    j.jogos,
    a.gols,
    a.assistencias,
    a.xG
FROM jogadores j
JOIN atacante_stats a ON j.id = a.jogador_id
WHERE j.posicao = 'ST';

-- 4.2. Listar Meio-Campistas com métricas avançadas (com Time)
SELECT
    j.nome,
    j.time, -- Puxando o time aqui
    j.idade,
    j.numero,
    m.assistencias,
    m.passes_certos,
    ROUND((m.passes_certos * 100.0) / m.passes_tentados, 1) AS precisao_passe
FROM jogadores j
INNER JOIN meio_stats m ON j.id = m.jogador_id
WHERE j.posicao = 'CM';

-- 4.3. Listar Defensores (com Time)
SELECT 
    j.nome,
    j.time, -- Puxando o time aqui
    j.idade,
    j.numero,
    d.desarmes,
    d.interceptacoes,
    d.cortes
FROM jogadores j
JOIN defensor_stats d ON j.id = d.jogador_id
WHERE j.posicao = 'CB';

-- 4.4. Listar Goleiros (com Time)
SELECT 
    j.nome,
    j.time, -- Puxando o time aqui
    j.idade,
    j.numero,
    g.defesas,
    g.gols_sofridos,
    g.clean_sheets
FROM jogadores j
JOIN goleiro_stats g ON j.id = g.jogador_id
WHERE j.posicao = 'GK';