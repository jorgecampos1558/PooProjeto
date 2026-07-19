/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooprojeto;

public abstract class Jogador {

    protected String nome;
    protected int idade;
    protected String posicao;
    protected int numero;
    protected int jogos;
    protected int jogosTitular;
    protected int minutosJogados;
    protected String time;

    public Jogador(String nome, int idade, String posicao, int numero,
                   int jogos, int jogosTitular, int minutosJogados, String time) {

        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.numero = numero;
        this.jogos = jogos;
        this.jogosTitular = jogosTitular;
        this.minutosJogados = minutosJogados;
        this.time = time;
    }

    public abstract double calcularNota();

    public String getNome() {
        return nome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}