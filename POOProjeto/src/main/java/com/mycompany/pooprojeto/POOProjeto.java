package com.mycompany.pooprojeto;

import Interface.TelaPrincipal;
import javax.swing.JFrame;

public class POOProjeto {

    public static void main(String[] args) {
        // Inicializa a interface de forma segura na thread do Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 1. Criamos a janela real (O Quadro)
                JFrame janela = new JFrame("Scout Pro - Planilha de Rendimento");
                
                // 2. Instanciamos a sua tela (A Folha Desenhada)
                TelaPrincipal meuPainel = new TelaPrincipal();
                
                // 3. Colocamos a sua tela dentro da janela
                janela.add(meuPainel);
                
                // 4. Configurações essenciais para a janela funcionar
                janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao clicar no X
                janela.pack(); // Ajusta o tamanho da janela automaticamente para abraçar seu painel (1652px)
                janela.setLocationRelativeTo(null); // Centraliza a janela perfeitamente na tela do PC
                
                // 5. Faz a mágica acontecer e exibe tudo
                janela.setVisible(true);
            }
        });
    }
}