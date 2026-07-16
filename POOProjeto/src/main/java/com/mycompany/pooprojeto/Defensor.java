/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooprojeto;

/**
 *
 * @author dacam
 */
public class Defensor extends Jogador {

    private int desarmes;
    private int interceptacoes;
    private int cortes;

    public Defensor(int desarmes, int interceptacoes, int cortes, int duelosGanhos, int faltas, String nome, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados) {
        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados);
        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
        this.cortes = cortes;
        this.duelosGanhos = duelosGanhos;
        this.faltas = faltas;
    }
    private int duelosGanhos;
    private int faltas;

    public Defensor(String nome, int idade, int numero,
                     int jogos, int jogosTitular, int minutosJogados,
                     int desarmes, int interceptacoes,
                     int cortes, int duelosGanhos,
                     int faltas) {

        super(nome, idade, "CB", numero,
              jogos, jogosTitular, minutosJogados);

        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
        this.cortes = cortes;
        this.duelosGanhos = duelosGanhos;
        this.faltas = faltas;
    }

@Override
public double calcularNota() {

double nota = 6.0;

double impactoDefensivo =
    (desarmes + interceptacoes + cortes) / 10.0;

double duelos =
    duelosGanhos / 10.0;

impactoDefensivo = Math.min(impactoDefensivo, 2.0);
duelos = Math.min(duelos, 2.0);

nota += impactoDefensivo * 1.5;
nota += duelos * 1.2;

nota -= faltas * 0.15;

    nota = Math.max(0, Math.min(nota, 10));
    return Math.round(nota * 10.0) / 10.0;
}
}
