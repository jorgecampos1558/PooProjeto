package com.mycompany.pooprojeto;

public class Defensor extends Jogador {

    private int desarmes;
    private int interceptacoes;
    private int cortes;
    private int duelosGanhos;
    private int duelosTentados;
    private int faltas;

    public Defensor(String nome, int idade, int numero, int jogos, int jogosTitular, int minutosJogados,
                    int desarmes, int interceptacoes, int cortes, int duelosGanhos, int duelosTentados, int faltas) {
        
        super(nome, idade, "CB", numero, jogos, jogosTitular, minutosJogados);
        
        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
        this.cortes = cortes;
        this.duelosGanhos = duelosGanhos;
        this.duelosTentados = duelosTentados;
        this.faltas = faltas;
    }

    public double calcularPorcentagemDuelosGanhos() {
        if (duelosTentados == 0) {
            return 0.0;
        }
        double pct = (duelosGanhos / (double) duelosTentados) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }

    public double calcularDuelosGanhosPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (duelosGanhos / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularDuelosTentadosPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (duelosTentados / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    @Override
public double calcularNota() {
    double nota = 6.0;

    if (minutosJogados == 0) return 0.0;

    double acoesDefensivasPor90 = ((desarmes + interceptacoes + cortes) / (double) minutosJogados) * 90;
    nota += acoesDefensivasPor90 * 0.4;

    double pctDuelos = calcularPorcentagemDuelosGanhos();
    if (pctDuelos > 60.0) {
        nota += (pctDuelos - 60.0) * 0.05;
    }

    int duelosPerdidos = duelosTentados - duelosGanhos;
    double duelosPerdidosPor90 = (duelosPerdidos / (double) minutosJogados) * 90;
    nota -= duelosPerdidosPor90 * 0.25;

    double faltasPor90 = (faltas / (double) minutosJogados) * 90;
    if (faltasPor90 > 1.2) {
        nota -= (faltasPor90 - 1.2) * 0.4;
    }

    return Math.round(Math.max(0.0, Math.min(nota, 10.0)) * 10.0) / 10.0;
}

    public int getDesarmes() { return desarmes; }
    public int getInterceptacoes() { return interceptacoes; }
    public int getCortes() { return cortes; }
    public int getDuelosGanhos() { return duelosGanhos; }
    public int getDuelosTentados() { return duelosTentados; }
    public int getFaltas() { return faltas; }
}