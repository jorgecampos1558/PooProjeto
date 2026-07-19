package com.mycompany.pooprojeto;

public class MeioCampista extends Jogador {

    private int assistencias;
    private int passesCertos;
    private int passesTentados;
    private int passesDecisivos;
    private int chancesCriadas;
    private int desarmes;
    private int interceptacoes;
    private int bolasRecuperadas;
    private int perdasDePosse;

    public MeioCampista(String nome, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados,
            String time, int assistencias, int passesCertos, int passesTentados, int passesDecisivos,
            int chancesCriadas, int desarmes, int interceptacoes, int bolasRecuperadas, int perdasDePosse) {

        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados = minutosJogados, time);
        
        this.assistencias = assistencias;
        this.passesCertos = passesCertos;
        this.passesTentados = passesTentados;
        this.passesDecisivos = passesDecisivos;
        this.chancesCriadas = chancesCriadas;
        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
        this.bolasRecuperadas = bolasRecuperadas;
        this.perdasDePosse = perdasDePosse;
    }

    public double calcularPrecisaoPasse() {
        if (passesTentados == 0) {
            return 0.0;
        }
        double pct = (passesCertos / (double) passesTentados) * 100.0;
        return Math.round(pct * 10.0) / 10.0;
    }

    public double calcularPassesDecisivosPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (passesDecisivos / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularAssistenciasPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (assistencias / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularDesarmesPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (desarmes / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularInterceptacoesPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (interceptacoes / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularBolasRecuperadasPor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (bolasRecuperadas / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    public double calcularxA() {
        return (assistencias * 0.4) + (passesDecisivos * 0.15) + (chancesCriadas * 0.10);
    }

    public double calcularxApor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        return (calcularxA() / minutosJogados) * 90.0;
    }

    public double calcularPerdasPossePor90() {
        if (minutosJogados == 0) {
            return 0.0;
        }
        double por90 = (perdasDePosse / (double) minutosJogados) * 90.0;
        return Math.round(por90 * 100.0) / 100.0;
    }

    @Override
    public double calcularNota() {
        if (minutosJogados == 0) {
            return 0.0;
        }

        double pc90 = calcularPassesDecisivosPor90();
        double ast90 = calcularAssistenciasPor90();
        double des90 = calcularDesarmesPor90();
        double int90 = calcularInterceptacoesPor90();
        double rec90 = calcularBolasRecuperadasPor90();

        double nota = 6.0;

        nota += (pc90 * 0.35);
        nota += (ast90 * 1.00);

        nota += (des90 * 0.15);
        nota += (int90 * 0.15);
        nota += (rec90 * 0.02);

        double precisao = calcularPrecisaoPasse();
        if (precisao > 85.0) {
            nota += 0.1;
        }

        nota = Math.max(0.0, Math.min(nota, 10.0));
        return Math.round(nota * 10.0) / 10.0;
    }
}