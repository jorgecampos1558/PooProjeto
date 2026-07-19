package com.mycompany.pooprojeto;

public class Goleiro extends Jogador {

    private int defesas;
    private int defesasDificeis;
    private int golsSofridos;
    private double xgContra;
    private int cleanSheets;
    private int penaltisDefendidos;

    public Goleiro(String nome, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados,
                   String time, int defesas, int defesasDificeis, int golsSofridos, double xgContra, 
                   int cleanSheets, int penaltisDefendidos) {

        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados, time);
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
        if (jogos == 0) return 0.0;

        double nota = 6.0;

        double pctDefesas = calcularPorcentagemDefesas();
        nota += (pctDefesas - 70.0) * 0.15;

        double golsPorJogo = calcularMediaGolsSofridos();
        nota -= (golsPorJogo * 0.6);

        double csPorJogo = cleanSheets / (double) jogos;
        nota += (csPorJogo * 2.5);

        double defDificeisPorJogo = defesasDificeis / (double) jogos;
        nota += (defDificeisPorJogo * 0.6);

        double gpPorJogo = calcularGolsPrevenidos() / (double) jogos;
        nota += (gpPorJogo * 3.5);

        double penaisPorJogo = penaltisDefendidos / (double) jogos;
        nota += (penaisPorJogo * 4.0);

        nota = Math.max(0.0, Math.min(nota, 10.0));
        return Math.round(nota * 10.0) / 10.0;
    }

    public int getCleanSheets() { 
        return cleanSheets; 
    }

    public int getPenaltisDefendidos() { 
        return penaltisDefendidos; 
    }
}