/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooprojeto;

public class MeioCampista extends Jogador {

    private int assistencias;
    private int passesCertos;
    private int passesTentados;
    private int desarmes;
    private int interceptacoes;
    private int passesDecisivos;
    private int chancesCriadas;

    public MeioCampista(String nome, int idade, int numero,
                        int jogos, int jogosTitular, int minutosJogados,
                        int assistencias, int passesCertos,
                        int passesTentados, int passesDecisivos,
                        int chancesCriadas, int desarmes,
                        int interceptacoes) {

        super(nome, idade, "CM", numero,
              jogos, jogosTitular, minutosJogados);

        this.assistencias = assistencias;
        this.passesCertos = passesCertos;
        this.passesTentados = passesTentados;
        this.passesDecisivos = passesDecisivos;
        this.chancesCriadas = chancesCriadas;
        this.desarmes = desarmes;
        this.interceptacoes = interceptacoes;
    }

    public double calcularPrecisaoPasse() {

        if (passesTentados == 0) {
            return 0.0;
        }

        double precisao =
                (passesCertos / (double) passesTentados) * 100;

        return Math.round(precisao * 10.0) / 10.0;
    }

    public double calcularAssistenciasPor90() {

        if (minutosJogados == 0) {
            return 0.0;
        }

        double valor =
                (assistencias / (double) minutosJogados) * 90;

        return Math.round(valor * 10.0) / 10.0;
    }

    public double calcularxA() {

        double xA = 0;

        xA += assistencias * 0.4;
        xA += passesDecisivos * 0.15;
        xA += chancesCriadas * 0.10;

        return Math.round(xA * 10.0) / 10.0;
    }

    public double calcularParticipacaoDefensiva() {

        return desarmes + interceptacoes;
    }

    @Override
    public double calcularNota() {

        double nota = 6.0;

        double precisaoPasse =
                calcularPrecisaoPasse();

        double assistenciasPor90 =
                calcularAssistenciasPor90();

        double xA =
                calcularxA();

        double participacaoDefensiva =
                calcularParticipacaoDefensiva();

        nota += precisaoPasse * 0.015;

        nota += assistenciasPor90 * 1.5;

        nota += xA * 0.25;

        nota += participacaoDefensiva * 0.03;

        nota = Math.max(0, Math.min(nota, 10));

        return Math.round(nota * 10.0) / 10.0;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public int getPassesCertos() {
        return passesCertos;
    }

    public int getPassesTentados() {
        return passesTentados;
    }

    public int getPassesDecisivos() {
        return passesDecisivos;
    }

    public int getChancesCriadas() {
        return chancesCriadas;
    }

    public int getDesarmes() {
        return desarmes;
    }

    public int getInterceptacoes() {
        return interceptacoes;
    }
}
