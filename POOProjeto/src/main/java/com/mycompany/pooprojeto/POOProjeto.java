/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pooprojeto;

public class POOProjeto {

    public static void main(String[] args) {

        JogadorDAO dao = new JogadorDAO();

        Atacante atacante = dao.buscarAtacante("Yuri Alberto");
        MeioCampista meio = dao.buscarMeioCampista("Rodrigo Garro");
        Defensor defensor = dao.buscarDefensor("Andre Ramalho");
        Goleiro goleiro = dao.buscarGoleiro("Hugo Souza");

        if (atacante != null) {
            System.out.println("=== ATACANTE ===");
            System.out.println("Nome: " + atacante.getNome());
            System.out.println("Nota: " + atacante.calcularNota());
            System.out.println("Precisão de Finalização: "
                    + atacante.calcularPrecisaoFinalizacao() + "%");
            System.out.println("Conversão em Gols: "
                    + atacante.calcularConversaoGols() + "%");
            System.out.println("Participação em Gols por 90: "
                    + atacante.calcularParticipacaoGolsPor90());
            System.out.println();
        }

        if (meio != null) {
            System.out.println("=== MEIO-CAMPISTA ===");
            System.out.println("Nome: " + meio.getNome());
            System.out.println("Nota: " + meio.calcularNota());
            System.out.println("Precisão de Passe: "
                    + meio.calcularPrecisaoPasse() + "%");
            System.out.println("Assistências por 90: "
                    + meio.calcularAssistenciasPor90());
            System.out.println("xA: "
                    + meio.calcularxA());
            System.out.println();
        }

        if (defensor != null) {
            System.out.println("=== DEFENSOR ===");
            System.out.println("Nome: " + defensor.getNome());
            System.out.println("Nota: " + defensor.calcularNota());
            System.out.println();
        }

        if (goleiro != null) {
            System.out.println("=== GOLEIRO ===");
            System.out.println("Nome: " + goleiro.getNome());
            System.out.println("Nota: " + goleiro.calcularNota());
            System.out.println();
        }

        dao.fecharConexao();

    }

}