package com.mycompany.pooprojeto;

public class POOProjeto {

    public static void main(String[] args) {

        JogadorDAO dao = new JogadorDAO();

        Atacante atacante = dao.buscarAtacante("Yuri Alberto");
        MeioCampista meio = dao.buscarMeioCampista("Matheuzinho");
        Defensor defensor = dao.buscarDefensor("Camutanga");
        Goleiro goleiro = dao.buscarGoleiro("Marcos Felipe");

        if (atacante != null) {
            System.out.println("=== ATACANTE ===");
            System.out.println("Nome: " + atacante.getNome());
            System.out.println("Time: " + atacante.getTime());
            System.out.println("Nota: " + atacante.calcularNota());
            System.out.println("Gols/90: " + atacante.calcularGolsPor90());
            System.out.println("xG/90: " + atacante.calcularXGpor90());
            System.out.println("Finalizações/90: " + atacante.calcularFinalizacoesPor90());
            System.out.println("Assistências/90: " + atacante.calcularAssistenciasPor90());
            System.out.println("Precisão de Finalização: " + atacante.calcularPrecisaoFinalizacao() + "%");
            System.out.println("Conversão em Gols: " + atacante.calcularConversaoGols() + "%");
            System.out.println("Dribles Certos/90: " + atacante.calcularDriblesPor90());
            System.out.println("Perdas de Posse/90: " + atacante.calcularPerdasPossePor90());
            System.out.println("Grandes Chances Perdidas/90: " + atacante.calcularGrandesChancesPerdidasPor90());
            System.out.println("Participação em Gols/90: " + atacante.calcularParticipacaoGolsPor90());
            System.out.println();
        }

        if (meio != null) {
            System.out.println("=== MEIO-CAMPISTA ===");
            System.out.println("Nome: " + meio.getNome());
            System.out.println("Time: " + meio.getTime());
            System.out.println("Nota: " + meio.calcularNota());
            System.out.println("Precisão de Passe: " + meio.calcularPrecisaoPasse() + "%");
            System.out.println("Passes Decisivos/90: " + meio.calcularPassesDecisivosPor90());
            System.out.println("Assistências/90: " + meio.calcularAssistenciasPor90());
            System.out.println("Desarmes/90: " + meio.calcularDesarmesPor90());
            System.out.println("Interceptações/90: " + meio.calcularInterceptacoesPor90());
            System.out.println("Bolas Recuperadas/90: " + meio.calcularBolasRecuperadasPor90());
            System.out.printf("xA: %.2f%n", meio.calcularxA());
            System.out.printf("xA/90: %.2f%n", meio.calcularxApor90());
            System.out.println("Perdas de Posse/90: " + meio.calcularPerdasPossePor90());
            System.out.println();
        }

       if (defensor != null) {
            System.out.println("=== DEFENSOR ===");
            System.out.println("Nome: " + defensor.getNome());
            System.out.println("Time: " + defensor.getTime());
            System.out.println("Nota: " + defensor.calcularNota());
            System.out.println("Percentagem de Duelos Ganhos: " + defensor.calcularPorcentagemDuelosGanhos() + "%");
            System.out.println("Percentagem de Duelos Aéreos Ganhos: " + defensor.calcularPorcentagemDuelosAereosGanhos() + "%");
            System.out.println("Duelos Ganhos/90: " + defensor.calcularDuelosGanhosPor90());
            System.out.println("Duelos Tentados/90: " + defensor.calcularDuelosTentadosPor90());
            System.out.println("Desarmes/90: " + defensor.calcularDesarmesPor90());
            System.out.println("Interceptações/90: " + defensor.calcularInterceptacoesPor90());
            System.out.println("Bolas Recuperadas/90: " + defensor.calcularBolasRecuperadasPor90());
            System.out.println("Duelos Aéreos Ganhos/90: " + defensor.calcularDuelosAereosGanhosPor90());
            System.out.println("Duelos Aéreos Tentados/90: " + defensor.calcularDuelosAereosTentadosPor90());
            System.out.println("Faltas/90: " + defensor.calcularFaltasPor90());
            System.out.println();
        }

        if (goleiro != null) {
            System.out.println("=== GOLEIRO ===");
            System.out.println("Nome: " + goleiro.getNome());
            System.out.println("Time: " + goleiro.getTime());
            System.out.println("Nota: " + goleiro.calcularNota());
            System.out.println("Porcentagem de Defesas: " + goleiro.calcularPorcentagemDefesas() + "%");
            System.out.println("Gols Prevenidos (vs xG): " + goleiro.calcularGolsPrevenidos());
            System.out.println("Defesas Difíceis/90: " + goleiro.calcularDefesasDificeisPor90());
            System.out.println("Média de Gols Sofridos por Jogo: " + goleiro.calcularMediaGolsSofridos());
            System.out.println("Clean Sheets: " + goleiro.getCleanSheets());
            System.out.println("Pênaltis Defendidos: " + goleiro.getPenaltisDefendidos());
            System.out.println();
        }

        dao.fecharConexao();
    }
}