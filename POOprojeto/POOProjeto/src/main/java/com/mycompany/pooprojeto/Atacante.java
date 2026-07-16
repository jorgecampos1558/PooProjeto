/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooprojeto;

public class Atacante extends Jogador {

    private int gols;
    private int assistencias;
    private int finalizacoes;
    private int finalizacoesNoGol;
    private int driblesCertos;
    private int perdasDePosse;

    public Atacante(String nome, int idade, int numero,
                    int jogos, int jogosTitular, int minutosJogados,
                    int gols, int assistencias,
                    int finalizacoes, int finalizacoesNoGol,
                    int driblesCertos, int perdasDePosse) {

        super(nome, idade, "ST", numero,
              jogos, jogosTitular, minutosJogados);

        this.gols = gols;
        this.assistencias = assistencias;
        this.finalizacoes = finalizacoes;
        this.finalizacoesNoGol = finalizacoesNoGol;
        this.driblesCertos = driblesCertos;
        this.perdasDePosse = perdasDePosse;
    }

    public double calcularPrecisaoFinalizacao() {

        if (finalizacoes == 0) {
            return 0.0;
        }

        double precisao =
                (finalizacoesNoGol / (double) finalizacoes) * 100;

        return Math.round(precisao * 10.0) / 10.0;
    }

    public double calcularConversaoGols() {

        if (finalizacoes == 0) {
            return 0.0;
        }

        double conversao =
                (gols / (double) finalizacoes) * 100;

        return Math.round(conversao * 10.0) / 10.0;
    }

    public double calcularParticipacaoGolsPor90() {

        if (minutosJogados == 0) {
            return 0.0;
        }

        double participacao =
                ((gols + assistencias) / (double) minutosJogados) * 90;

        return Math.round(participacao * 10.0) / 10.0;
    }

    @Override
    public double calcularNota() {
    double nota = 6.0;

    if (minutosJogados == 0) return 0.0;


    double golsPor90 = (gols / (double) minutosJogados) * 90;
    double assistenciasPor90 = (assistencias / (double) minutosJogados) * 90;
    double xG = (finalizacoes * 0.1) + (finalizacoesNoGol * 0.3);


    golsPor90 = Math.min(golsPor90, 1.2);
    assistenciasPor90 = Math.min(assistenciasPor90, 0.8);
    xG = Math.min(xG, 2.0);

    nota += golsPor90 * 2.0;
    nota += assistenciasPor90 * 1.2;
    nota += xG * 0.5;
    nota += (calcularPrecisaoFinalizacao() / 100.0) * 1.0; 
    nota += (driblesCertos / (double) minutosJogados) * 90 * 0.3; 


    int chutesParaFora = finalizacoes - finalizacoesNoGol;
    double chutesParaForaPor90 = (chutesParaFora / (double) minutosJogados) * 90;
    nota -= chutesParaForaPor90 * 0.35;


    double perdasPor90 = (perdasDePosse / (double) minutosJogados) * 90;
    if (perdasPor90 > 10.0) { 

        nota -= (perdasPor90 - 10.0) * 0.15;
    }

    return Math.round(Math.max(0.0, Math.min(nota, 10.0)) * 10.0) / 10.0;
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
}