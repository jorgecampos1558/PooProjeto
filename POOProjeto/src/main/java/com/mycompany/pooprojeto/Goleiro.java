/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooprojeto;

/**
 *
 * @author dacam
 */

public class Goleiro extends Jogador {

    private int defesas;
    private int defesasDificeis;
    private int golsSofridos;

    public Goleiro(int defesas, int defesasDificeis, int golsSofridos, int cleanSheets, int penaltisDefendidos, String nome, int idade, String posicao, int numero, int jogos, int jogosTitular, int minutosJogados) {
        super(nome, idade, posicao, numero, jogos, jogosTitular, minutosJogados);
        this.defesas = defesas;
        this.defesasDificeis = defesasDificeis;
        this.golsSofridos = golsSofridos;
        this.cleanSheets = cleanSheets;
        this.penaltisDefendidos = penaltisDefendidos;
    }
    private int cleanSheets;
    private int penaltisDefendidos;

    public Goleiro(String nome, int idade, int numero,
                    int jogos, int jogosTitular, int minutosJogados,
                    int defesas, int defesasDificeis,
                    int golsSofridos, int cleanSheets,
                    int penaltisDefendidos) {

        super(nome, idade, "GK", numero,
              jogos, jogosTitular, minutosJogados);

        this.defesas = defesas;
        this.defesasDificeis = defesasDificeis;
        this.golsSofridos = golsSofridos;
        this.cleanSheets = cleanSheets;
        this.penaltisDefendidos = penaltisDefendidos;
    }

    @Override
public double calcularNota() {

    double taxaDefesa =
    defesas / (double) Math.max(defesas + golsSofridos, 1);

    taxaDefesa = Math.min(taxaDefesa, 0.85);

    double nota = 6.0;

    nota += taxaDefesa * 4.0;
    nota += cleanSheets * 0.4;
    nota += defesasDificeis * 0.2;
    nota += penaltisDefendidos * 1.0;

    nota -= golsSofridos * 0.15;

    nota = Math.max(0, Math.min(nota, 10));
    return Math.round(nota * 10.0) / 10.0;
}
}
