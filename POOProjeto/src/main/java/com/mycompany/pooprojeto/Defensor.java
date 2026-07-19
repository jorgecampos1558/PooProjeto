package com.mycompany.pooprojeto;

public class Defensor extends Jogador {

    private int id; 
    private int desarmes;
    private int interceptacoes;
    private int cortes;
    private int duelosGanhos;
    private int duelosTentados;
    private int faltas;
    private int duelosaereosGanhos;
    private int duelosaereosTentados;
    private int bolasRecuperadas;

    public Defensor(String nome, int id, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados,
                    String time, int desarmes, int interceptacoes, int cortes, int duelosGanhos, int duelosTentados, 
                    int faltas, int duelosaereosGanhos, int duelosaereosTentados, int bolasRecuperadas) {

        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados, time);
        this.id = id;
        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
        this.cortes = cortes;
        this.duelosGanhos = duelosGanhos;
        this.duelosTentados = duelosTentados;
        this.faltas = faltas;
        this.duelosaereosGanhos = duelosaereosGanhos;
        this.duelosaereosTentados = duelosaereosTentados;
        this.bolasRecuperadas = bolasRecuperadas;
    }

    public double calcularPorcentagemDuelosGanhos() {
        if (duelosTentados == 0) return 0.0;
        double pct = (duelosGanhos / (double) duelosTentados) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }

    public double calcularPorcentagemDuelosAereosGanhos() {
        if (duelosaereosTentados == 0) return 0.0;
        double pct = (duelosaereosGanhos / (double) duelosaereosTentados) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }

    public double calcularDuelosGanhosPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (duelosGanhos / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularDuelosTentadosPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (duelosTentados / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularDuelosAereosGanhosPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (duelosaereosGanhos / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularDuelosAereosTentadosPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (duelosaereosTentados / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularFaltasPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (faltas / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }
    
    public double calcularDesarmesPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (desarmes / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularInterceptacoesPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (interceptacoes / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularBolasRecuperadasPor90() {
        if (minutosJogados == 0) return 0.0;
        double por90 = (bolasRecuperadas / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    @Override
    public double calcularNota() {
        if (minutosJogados == 0 || jogos == 0) return 0.0;

        double groundEff = duelosTentados == 0 ? 0.0 : (duelosGanhos / (double) duelosTentados) * 3.0;
        double aerialEff = duelosaereosTentados == 0 ? 0.0 : (duelosaereosGanhos / (double) duelosaereosTentados) * 3.0;

        double totalActions = desarmes + interceptacoes + cortes;
        double actionsPer90 = (totalActions * 90.0) / minutosJogados;
        double volumeScore = (Math.min(actionsPer90, 7.0) / 7.0) * 3.0;

        double faltasPor90 = calcularFaltasPor90();
        double penalty = faltasPor90 > 1.5 ? (faltasPor90 - 1.5) * 0.5 : 0.0;
        double disciplineScore = Math.max(1.0 - penalty, 0.0);

        double nota = groundEff + aerialEff + volumeScore + disciplineScore;

        return Math.round(Math.max(0.0, Math.min(nota, 10.0)) * 10.0) / 10.0;
    }

    public int getDesarmes() { return desarmes; }
    public int getInterceptacoes() { return interceptacoes; }
    public int getCortes() { return cortes; }
    public int getDuelosGanhos() { return duelosGanhos; }
    public int getDuelosTentados() { return duelosTentados; }
    public int getFaltas() { return faltas; }
    public int getDuelosaereosGanhos() { return duelosaereosGanhos; }
    public int getDuelosaereosTentados() { return duelosaereosTentados; }
}