/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooprojeto;

public class MeioCampista extends Jogador {

    public MeioCampista(int assistencias, int passesCertos, int passesErrados, int desarmes, int interceptacoes, String nome, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados) {
        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados);
        this.assistencias = assistencias;
        this.passesCertos = passesCertos;
        this.passesTentados = passesTentados;
        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
    }

    private int assistencias;
    private int passesCertos;
    private int passesTentados;
    private int desarmes;
    private int interceptacoes;

    public MeioCampista(String nome, int idade, int numero,
                     int jogos, int jogosTitular, int minutosJogados,
                     int assistencias, int passesCertos,
                     int passesTentados, int desarmes,
                     int interceptacoes) {

    super(nome, idade, "CM", numero,
          jogos, jogosTitular, minutosJogados);

    this.assistencias = assistencias;
    this.passesCertos = passesCertos;
    this.passesTentados = passesTentados;
    this.desarmes = desarmes;
    this.interceptacoes = interceptacoes;
}

@Override
public double calcularNota() {

    double precisao =
    (double) passesCertos / Math.max(passesTentados, 1);

    precisao = Math.min(precisao, 0.92);

    double nota = 6.0;

    nota += precisao * 3.5;
    nota += assistencias * 0.7;
    nota += (desarmes + interceptacoes) * 0.1;

    nota -= (1.0 - precisao) * 2.0;

    nota = Math.max(0, Math.min(nota, 10));
    return Math.round(nota * 10.0) / 10.0;
}

}
