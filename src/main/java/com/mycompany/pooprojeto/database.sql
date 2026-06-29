DROP DATABASE scoutpro;
CREATE DATABASE scoutpro;
USE scoutpro;

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
    faltas INT,
    FOREIGN KEY (jogador_id) REFERENCES jogadores(id)
);

CREATE TABLE goleiro_stats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jogador_id INT,
    defesas INT,
    defesas_dificeis INT,
    gols_sofridos INT,
    clean_sheets INT,
    penaltis_defendidos INT,
    FOREIGN KEY (jogador_id) REFERENCES jogadores(id)
);

INSERT INTO jogadores (nome, idade, posicao, numero, jogos, jogos_titular, minutos_jogados)
VALUES
('Hugo Souza', 27, 'GOL', 1, 30, 30, 2700),
('Matheus Donelli', 23, 'GOL', 32, 10, 2, 900),
('Félix Torres', 27, 'ZAG', 3, 28, 27, 2400),
('André Ramalho', 33, 'ZAG', 5, 25, 24, 2100),
('Gustavo Henrique', 32, 'ZAG', 13, 23, 23, 2070),
('Cacá', 25, 'ZAG', 25, 20, 15, 1600),
('Matheuzinho', 25, 'LAT', 2, 29, 27, 2500),
('Fabrizio Angileri', 30, 'LAT', 26, 18, 12, 1200),
('Matheus Bidu', 25, 'LAT', 21, 26, 18, 1900),
('José Martínez', 31, 'VOL', 70, 28, 24, 2200),
('Raniele', 27, 'VOL', 14, 30, 28, 2600),
('Maycon', 27, 'VOL', 7, 22, 18, 1700),
('Rodrigo Garro', 28, 'MEI', 8, 34, 32, 2750),
('Giovane', 20, 'MEI', 17, 15, 5, 600),
('Wesley', 19, 'ATA', 36, 28, 20, 1800),
('Ángel Romero', 32, 'ATA', 11, 33, 25, 2100),
('Yuri Alberto', 25, 'ATA', 9, 26, 21, 2007),
('Pedro Raul', 29, 'ATA', 18, 20, 10, 1100),
('Pedro Henrique', 33, 'ATA', 7, 24, 18, 1600),
('Garro Filho (base fictício controlado)', 19, 'MEI', 40, 12, 3, 400),
('Arthur Sousa', 21, 'ATA', 38, 10, 2, 300),
('Ryan', 21, 'VOL', 45, 14, 5, 500);

INSERT INTO atacante_stats (jogador_id, gols, assistencias, finalizacoes, finalizacoes_no_gol, dribles_certos, perdas_de_posse)
VALUES
(15, 5, 3, 40, 18, 30, 25),
(16, 7, 4, 60, 25, 35, 30),
(17, 10, 2, 50, 20, 15, 25),
(18, 4, 1, 35, 12, 20, 22),
(19, 6, 2, 45, 15, 18, 20),
(21, 2, 0, 20, 8, 10, 15);

INSERT INTO meio_stats(jogador_id, assistencias, passes_certos, passes_tentados, passes_decisivos, chances_criadas, desarmes, interceptacoes)
VALUES
(10, 3, 800, 950, 25, 18, 40, 35),
(11, 4, 1200, 1350, 15, 10, 55, 48),
(12, 2, 700, 900, 8, 6, 30, 25),
(13, 6, 1500, 1700, 55, 40, 45, 30),
(14, 1, 300, 400, 10, 8, 15, 10),
(20, 2, 250, 320, 6, 5, 12, 8),
(22, 1, 200, 280, 4, 3, 10, 7);

INSERT INTO defensor_stats (jogador_id, desarmes, interceptacoes, cortes, duelos_ganhos, faltas)
VALUES
(3, 55, 38, 80, 72, 20),
(4, 50, 35, 75, 68, 18),
(5, 52, 34, 70, 65, 22),
(6, 40, 30, 60, 55, 25),
(7, 60, 40, 85, 78, 19),
(8, 35, 25, 50, 48, 15),
(9, 45, 28, 65, 60, 21);

INSERT INTO goleiro_stats VALUES
(NULL, 1, 95, 28, 28, 13, 2),
(NULL, 2, 40, 10, 18, 3, 0);

ALTER TABLE jogadores ADD UNIQUE (nome, numero);


SELECT * FROM jogadores;

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
WHERE j.posicao = 'ATA';


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

    ROUND(
        (m.passes_certos * 100.0) /
        m.passes_tentados,
        1
    ) AS precisao_passe,

    ROUND(
        (m.assistencias * 90.0) /
        j.minutos_jogados,
        2
    ) AS assistencias_por90,

    ROUND(
        (m.assistencias * 0.4) +
        (m.passes_decisivos * 0.15) +
        (m.chances_criadas * 0.10),
        1
    ) AS xA

FROM jogadores j
INNER JOIN meio_stats m
ON j.id = m.jogador_id

WHERE j.posicao IN ('MEI', 'VOL');


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
    d.faltas
FROM jogadores j
JOIN defensor_stats d ON j.id = d.jogador_id
WHERE j.posicao = 'ZAG';

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
WHERE j.posicao = 'GOL';
