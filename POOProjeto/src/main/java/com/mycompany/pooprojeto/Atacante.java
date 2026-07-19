package com.mycompany.pooprojeto;

public class Atacante extends Jogador {

    private int gols;
    private int assistencias;
    private int finalizacoes;
    private int finalizacoesNoGol;
    private int driblesCertos;
    private int perdasDePosse;
    private double xg;
    private int grandesChancesPerdidas;

    public Atacante(String nome, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados,
            String time, int gols, int assistencias, int finalizacoes, int finalizacoesNoGol, int driblesCertos,
            int perdasDePosse, double xg, int grandesChancesPerdidas) {

        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados, time);
        
        this.gols = gols;
        this.assistencias = assistencias;
        this.finalizacoes = finalizacoes;
        this.finalizacoesNoGol = finalizacoesNoGol;
        this.driblesCertos = driblesCertos;
        this.perdasDePosse = perdasDePosse;
        this.xg = xg;
        this.grandesChancesPerdidas = grandesChancesPerdidas;
    }

    public double calcularPrecisaoFinalizacao() {
        if (finalizacoes == 0) {
            return 0.0;
        }
        double pct = (finalizacoesNoGol / (double) finalizacoes) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }

    public double calcularConversaoGols() {
        if (finalizacoes == 0) {
            return 0.0;
        }
        double pct = (gols / (double) finalizacoes) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }
    
    public double calcularAssistenciasPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (assistencias / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularParticipacaoGolsPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = ((gols + assistencias) / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularGolsPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (gols / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularFinalizacoesPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (finalizacoes / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularDriblesPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (driblesCertos / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularPerdasPossePor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (perdasDePosse / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularXGpor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (xg / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularGrandesChancesPerdidasPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (grandesChancesPerdidas / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    @Override
    public double calcularNota() {
        if (minutosJogados == 0) {
            return 0.0;
        }

        double golsPor90 = calcularGolsPor90();
        double xgPor90 = calcularXGpor90();
        double assistPor90 = (assistencias / (double) minutosJogados) * 90.0;
        double driblesPor90 = calcularDriblesPor90();
        double perdasPor90 = calcularPerdasPossePor90();
        double chancesPerdidasPor90 = calcularGrandesChancesPerdidasPor90();

        double nota = 6.0;
        nota += (golsPor90 * 1.2);
        nota += (xgPor90 * 0.4);
        nota += (assistPor90 * 0.5);
        nota += (driblesPor90 * 0.2);

        nota -= (perdasPor90 * 0.1);
        nota -= (chancesPerdidasPor90 * 0.25);

        double precisao = calcularPrecisaoFinalizacao();
        if (precisao > 45.0) {
            nota += 0.2;
        }

        nota = Math.max(0.0, Math.min(nota, 10.0));
        return Math.round(nota * 10.0) / 10.0;
    }

    public int getGols() {
        return gols;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public int getFinalizacoes() {
        return finalizacoes;
    }

    public int getFinalizacoesNoGol() {
        return finalizacoesNoGol;
    }

    public int getDriblesCertos() {
        return driblesCertos;
    }

    public int getPerdasDePosse() {
        return perdasDePosse;
    }

    public double getXg() {
        return xg;
    }

    public int getGrandesChancesPerdidas() {
        return grandesChancesPerdidas;
    }
}