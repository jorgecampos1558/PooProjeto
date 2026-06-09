/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pooprojeto;

/**
 *
 * @author dacam
 */
public class POOProjeto {

    public static void main(String[] args) {

Atacante atacante = new Atacante(
    "Yuri Alberto",
    25,     //idade
    9,      //numero
    26,     // jogos
    21,     // titular
    2007,   // minutos

    10,     // gols
    2,      // assistências
    50,     // finalizações
    20,     // finalizações no gol
    15,     // dribles certos
    25      // perdas de posse
);
        
MeioCampista meio = new MeioCampista(
    "Rodrigo Garro",
    28,     //idade
    8,      //numero
    34,     //jogos
    32,     //titular
    2750,   //minutos

    6,      // assistências
    900,    // passes certos
    1100,   // passes tentados
    30,     // desarmes
    18      // interceptações
);

Defensor defensor = new Defensor(
    "Gustavo Henrique",
    32,     //idade
    13,     //numero
    23,     //jogos
    23,     //titular
    2070,   //minutos

    3,      // gols
    38,     // interceptações
    44,     // cortes
    70,     // duelos ganhos
    18      // faltas
);

Goleiro goleiro = new Goleiro(
    "Hugo Souza",
    27,     //idade
    1,      //numero
    30,     //jogos
    30,     //titular
    2700,   //minutos

    95,     // defesas
    28,     // defesas difíceis
    28,     // gols sofridos
    13,     // clean sheets
    2       // pênaltis defendidos
);

        System.out.println(atacante.getNome()
                + " Nota: " + atacante.calcularNota());
        
        System.out.println(meio.getNome()
                + " Nota: "+ meio.calcularNota());


        System.out.println(defensor.getNome()
                + " Nota: " + defensor.calcularNota());

        System.out.println(goleiro.getNome()
                + " Nota: " + goleiro.calcularNota());
    }
}
