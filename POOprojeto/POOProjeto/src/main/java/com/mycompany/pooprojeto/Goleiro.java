package com.mycompany.pooprojeto;

public class Goleiro extends Jogador {

    private int defesas;
    private int defesasDificeis;
    private int golsSofridos;
    private double xgContra;
    private int cleanSheets;
    private int penaltisDefendidos;

    public Goleiro(String nome, int idade, int numero, int jogos, int jogosTitular, int minutosJogados,
                   int defesas, int defesasDificeis, int golsSofridos, double xgContra, int cleanSheets, int penaltisDefendidos) {
        
        super(nome, idade, "GK", numero, jogos, jogosTitular, minutosJogados);
        this.defesas = defesas;
        this.defesasDificeis = defesasDificeis;
        this.golsSofridos = golsSofridos;
        this.xgContra = xgContra;
        this.cleanSheets = cleanSheets;
        this.penaltisDefendidos = penaltisDefendidos;
    }

    public double calcularPorcentagemDefesas() {
        int chutesAoGol = defesas + golsSofridos;
        if (chutesAoGol == 0) return 0.0;
        double pct = (defesas / (double) chutesAoGol) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }

    public double calcularGolsPrevenidos() {
        double gp = xgContra - golsSofridos;
        return Math.round(gp * 10.0) / 10.0;
    }

    public double calcularDefesasDificeisPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (defesasDificeis / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularMediaGolsSofridos() {
        if (jogos == 0) return 0.0;
        double media = golsSofridos / (double) jogos;
        return Math.round(media * 100.0) / 100.0;
    }

    @Override
public double calcularNota() {
    double nota = 6.0;

    if (jogos == 0) return 0.0;

    double taxaDefesa = defesas / (double) Math.max(defesas + golsSofridos, 1);
    nota += (taxaDefesa * 100.0 - 70.0) * 0.1;

    double defesasDificeisPorJogo = defesasDificeis / (double) jogos;
    nota += defesasDificeisPorJogo * 0.4;
    nota += cleanSheets * 0.15;
    nota += penaltisDefendidos * 0.6;


    double mediaGols = golsSofridos / (double) jogos;
    if (mediaGols > 1.0) {
        nota -= (mediaGols - 1.0) * 0.5;
    }

    double golsPrevenidos = xgContra - golsSofridos;
    if (golsPrevenidos < 0) {
    nota -= Math.abs(golsPrevenidos) * 0.25;
    }

    return Math.round(Math.max(0.0, Math.min(nota, 10.0)) * 10.0) / 10.0;
}

    public int getDefesas() { return defesas; }
    public int getDefesasDificeis() { return defesasDificeis; }
    public int getGolsSofridos() { return golsSofridos; }
    public double getXgContra() { return xgContra; }
    public int getCleanSheets() { return cleanSheets; }
    public int getPenaltisDefendidos() { return penaltisDefendidos; }
}