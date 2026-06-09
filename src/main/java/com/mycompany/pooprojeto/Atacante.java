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

@Override
public double calcularNota() {

double nota = 6.0;

double golsPor90 =
    (gols / (double) minutosJogados) * 90;

double assistenciasPor90 =
    (assistencias / (double) minutosJogados) * 90;

double xG =
    (finalizacoes * 0.1) + (finalizacoesNoGol * 0.3);

double conversao =
    gols / (double) Math.max(finalizacoes, 1);

golsPor90 = Math.min(golsPor90, 1.2);
assistenciasPor90 = Math.min(assistenciasPor90, 0.8);
xG = Math.min(xG, 2.0);
conversao = Math.min(conversao, 0.7);

nota += golsPor90 * 1.5;
nota += assistenciasPor90 * 1.0;
nota += xG * 0.6;
nota += conversao * 1.8;

nota += driblesCertos * 0.02;
nota -= perdasDePosse * 0.05;

    nota = Math.max(0, Math.min(nota, 10));
    return Math.round(nota * 10.0) / 10.0;
}

}