/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pooprojeto;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TelaPrincipal extends javax.swing.JPanel {

    private javax.swing.JTable tabelaFixa;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();

        tabelaFixa = new javax.swing.JTable();
        tabelaFixa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaFixa.setSelectionModel(tabelaScout.getSelectionModel());
        tabelaFixa.setModel(tabelaScout.getModel());
        tabelaFixa.setFocusable(false);
        tabelaFixa.setShowGrid(true);

        // Aplica o renderizador nas duas tabelas
        HeaderMultiCorRenderer customHeader = new HeaderMultiCorRenderer();
        tabelaScout.getTableHeader().setDefaultRenderer(customHeader);
        tabelaFixa.getTableHeader().setDefaultRenderer(customHeader);

        jScrollPane1.setRowHeaderView(tabelaFixa);
        jScrollPane1.setCorner(javax.swing.JScrollPane.UPPER_LEFT_CORNER, tabelaFixa.getTableHeader());

        tabelaScout.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 45));
        tabelaFixa.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 45));
        
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        cbPosicaoActionPerformed(null);
    }
    
   private String fmt(Object valor) {
        if (valor == null) {
            return "0,00";
        }
        if (valor instanceof Number) {
            return String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", ((Number) valor).doubleValue());
        }
        return valor.toString();
    }

    public void ajustarLarguraColunas(javax.swing.JTable tabela) {
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        for (int coluna = 0; coluna < tabela.getColumnCount(); coluna++) {
            javax.swing.table.TableColumn tableColumn = tabela.getColumnModel().getColumn(coluna);

            int larguraMaxima = 0;
            Object headerValue = tableColumn.getHeaderValue();
            if (headerValue != null) {
                javax.swing.table.TableCellRenderer headerRenderer = tabela.getTableHeader().getDefaultRenderer();
                java.awt.Component compHeader = headerRenderer.getTableCellRendererComponent(
                        tabela, headerValue, false, false, -1, coluna);
                larguraMaxima = compHeader.getPreferredSize().width;
            }

            for (int linha = 0; linha < tabela.getRowCount(); linha++) {
                javax.swing.table.TableCellRenderer cellRenderer = tabela.getCellRenderer(linha, coluna);
                java.awt.Component compCelula = tabela.prepareRenderer(cellRenderer, linha, coluna);
                larguraMaxima = Math.max(compCelula.getPreferredSize().width, larguraMaxima);
            }

            tableColumn.setPreferredWidth(larguraMaxima + 25);
        }
    }
    

public class HeaderMultiCorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(Color.WHITE);

        String headerText = (value != null) 
                ? value.toString().replaceAll("<[^>]*>", "").toLowerCase() 
                : "";

        // --- DEFINIÇÃO DE CORES POR CATEGORIA ---

        if (headerText.contains("assist") || headerText.contains("xa") || 
                   headerText.contains("participaç") || headerText.contains("passe")) {
            
             label.setBackground(new Color(230, 170, 20));
            label.setForeground(Color.BLACK); // Texto preto para leitura no amarelo

        } else if (headerText.contains("gol") || headerText.contains("xg") || 
            headerText.contains("finaliz") || headerText.contains("conversão") || 
            headerText.contains("chance")){
            
             label.setBackground(new Color(180, 40, 40));

        } else if (headerText.contains("desarme") || headerText.contains("intercept") || 
                   headerText.contains("duelo") || headerText.contains("corte") || 
                   headerText.contains("bloque") || headerText.contains("recupera") || 
                   headerText.contains("aéreo") || headerText.contains("falta cometi")) {
            
            label.setBackground(new Color(30, 132, 73)); // Verde Esmeralda

        } else if (headerText.contains("perda") || headerText.contains("posse")) {
            
            label.setBackground(new Color(52, 152, 219));

        } else if (headerText.contains("clean") || headerText.contains("prevenido") || 
                   headerText.contains("defesa de goleiro") || headerText.contains("pênalti defend")) {
            
            label.setBackground(new Color(180, 90, 20));

        } else if (headerText.contains("nome") || headerText.contains("time") || headerText.contains("nacionalidade") ||
                   headerText.contains("idade") || headerText.contains("nº") || headerText.contains("jogos") ||
                   headerText.contains("minutos") || headerText.contains("nota")) {
            
            label.setBackground(new Color(44, 62, 80));

        } else {
            label.setBackground(new Color(100, 60, 130));
        }

        return label;
    }
}
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbPosicao = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaScout = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Posição");

        cbPosicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atacante", "Meio-Campista", "Defensor", "Goleiro" }));
        cbPosicao.addActionListener(this::cbPosicaoActionPerformed);

        tabelaScout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        tabelaScout.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaScout.setShowGrid(true);
        jScrollPane1.setViewportView(tabelaScout);

        jButton1.setText("Filtros");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(cbPosicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbPosicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPosicaoActionPerformed
if (cbPosicao.getSelectedItem() == null) return;

    String posicaoSelecionada = cbPosicao.getSelectedItem().toString();
    DefaultTableModel modelo = (DefaultTableModel) tabelaScout.getModel();

    tabelaScout.setAutoCreateColumnsFromModel(true);
    tabelaFixa.setAutoCreateColumnsFromModel(true);

    modelo.setColumnCount(0);
    modelo.setRowCount(0);

    JogadorDAO dao = new JogadorDAO();

    try {
        if (posicaoSelecionada.equals("Atacante")) {
    modelo.addColumn("Nome");
    modelo.addColumn("Time");
    modelo.addColumn("Nacionalidade");
    modelo.addColumn("Idade");
    modelo.addColumn("Nº");
    modelo.addColumn("Jogos");
    modelo.addColumn("<html><center>Jogos como<br>Titular</center></html>");
    modelo.addColumn("<html><center>Minutos<br>Jogados</center></html>");
    modelo.addColumn("Nota");
    
    modelo.addColumn("Gols");
    modelo.addColumn("Gols Esperados (xG)");
    modelo.addColumn("Gols por 90 Minutos");
    modelo.addColumn("xG por 90 Minutos");
    modelo.addColumn("Conversão de Gols");
    modelo.addColumn("Finalizações");
    modelo.addColumn("<html><center>Finalizações<br>no Gol</center></html>");
    modelo.addColumn("Finalizações por 90 Minutos");
    modelo.addColumn("Precisão de Finalização");
    modelo.addColumn("<html><center>Grandes Chances<br>Perdidas</center></html>");
    modelo.addColumn("<html><center>Grandes Chances<br>Perdidas por 90 Minutos</center></html>");

    modelo.addColumn("Assistências");
    modelo.addColumn("Assistências por 90 Minutos");
    modelo.addColumn("<html><center>Participação em Gols<br>por 90 Minutos</center></html>");

    modelo.addColumn("Perdas de Posse");
    modelo.addColumn("Perdas de Posse por 90 Minutos");

    modelo.addColumn("Dribles Certos");
    modelo.addColumn("Dribles Certos por 90 Minutos");

// --- DADOS DO ATACANTE ---
    java.util.List<Atacante> lista = dao.listarAtacantes();
    for (Atacante atacante : lista) {
        modelo.addRow(new Object[]{
            // Básico
            atacante.getNome(),
            atacante.getTime(), 
            atacante.getNacionalidade(),
            atacante.getIdade(), 
            atacante.getNumero(), 
            atacante.getJogos(), 
            atacante.getJogosTitular(), 
            atacante.getMinutosJogados(),
            fmt(atacante.calcularNota()),
            
            // Ataque / Gols (Vermelho)
            atacante.getGols(),
            fmt(atacante.getXg()),
            fmt(atacante.calcularGolsPor90()),
            fmt(atacante.calcularXGpor90()),
            fmt(atacante.calcularConversaoGols()) + "%",
            atacante.getFinalizacoes(),
            atacante.getFinalizacoesNoGol(),
            fmt(atacante.calcularFinalizacoesPor90()),
            fmt(atacante.calcularPrecisaoFinalizacao()) + "%",
            atacante.getGrandesChancesPerdidas(),
            fmt(atacante.calcularGrandesChancesPerdidasPor90()),
            
            // Assistências (Amarelo)
            atacante.getAssistencias(),
            fmt(atacante.calcularAssistenciasPor90()),
            fmt(atacante.calcularParticipacaoGolsPor90()),
            
            // Perdas de Posse (Azul Claro)
            atacante.getPerdasDePosse(),
            fmt(atacante.calcularPerdasPossePor90()),

            // Dribles (Roxo)
            atacante.getDriblesCertos(),
            fmt(atacante.calcularDriblesPor90())
        });
    }
}
        else if (posicaoSelecionada.equals("Meio-Campista")) {
            // --- COLUNAS DO MEIO-CAMPISTA ---
            modelo.addColumn("Nome");
            modelo.addColumn("Time");
            modelo.addColumn("Nacionalidade");
            modelo.addColumn("Idade");
            modelo.addColumn("Nº");
            modelo.addColumn("Jogos");
            modelo.addColumn("<html><center>Jogos como<br>Titular</center></html>");
            modelo.addColumn("<html><center>Minutos<br>Jogados</center></html>");
            modelo.addColumn("Nota");
            
            modelo.addColumn("Assistências");
            modelo.addColumn("Assistências Esperadas (xA) Total");
            modelo.addColumn("Assistências por 90 Minutos");
            modelo.addColumn("xA por 90 Minutos");
            modelo.addColumn("Passes Decisivos por 90 Minutos");
            modelo.addColumn("Precisão de Passe");
            modelo.addColumn("Desarmes por 90 Minutos");
            modelo.addColumn("Interceptações por 90 Minutos");
            modelo.addColumn("Bolas Recuperadas por 90 Minutos");
            modelo.addColumn("Perdas de Posse por 90 Minutos");

            java.util.List<MeioCampista> lista = dao.listarMeioCampistas();
            for (MeioCampista meio : lista) {
                modelo.addRow(new Object[]{
                    meio.getNome(),
                    meio.getTime(), 
                    meio.getNacionalidade(),
                    meio.getIdade(), 
                    meio.getNumero(), 
                    meio.getJogos(), 
                    meio.getJogosTitular(), 
                    meio.getMinutosJogados(),
                    fmt(meio.calcularNota()),
                    meio.getAssistencias(),
                    fmt(meio.calcularxA()),
                    fmt(meio.calcularAssistenciasPor90()),
                    fmt(meio.calcularxApor90()),
                    fmt(meio.calcularPassesDecisivosPor90()),
                    fmt(meio.calcularPrecisaoPasse()) + "%",
                    fmt(meio.calcularDesarmesPor90()),
                    fmt(meio.calcularInterceptacoesPor90()),
                    fmt(meio.calcularBolasRecuperadasPor90()),
                    fmt(meio.calcularPerdasPossePor90())
                });
            }
        } 
        else if (posicaoSelecionada.equals("Defensor")) {
            // --- COLUNAS DO DEFENSOR ---
            modelo.addColumn("Nome");
            modelo.addColumn("Time");
            modelo.addColumn("Nacionalidade");
            modelo.addColumn("Idade");
            modelo.addColumn("Nº");
            modelo.addColumn("Jogos");
            modelo.addColumn("<html><center>Jogos como<br>Titular</center></html>");
            modelo.addColumn("<html><center>Minutos<br>Jogados</center></html>");
            modelo.addColumn("Nota");
            
            modelo.addColumn("Desarmes");
            modelo.addColumn("Desarmes por 90 Minutos");
            modelo.addColumn("Interceptações");
            modelo.addColumn("Interceptações por 90 Minutos");
            modelo.addColumn("Cortes");
            modelo.addColumn("Bolas Recuperadas por 90 Minutos");
            modelo.addColumn("Faltas");
            modelo.addColumn("Faltas por 90 Minutos");
            modelo.addColumn("Duelos no Chão Ganhos");
            modelo.addColumn("Duelos no Chão Tentados");
            modelo.addColumn("Duelos no Chão Ganhos por 90 Minutos");
            modelo.addColumn("Duelos no Chão Tentados por 90 Minutos");
            modelo.addColumn("Porcentagem de Duelos Ganhos");
            modelo.addColumn("Duelos Aéreos Ganhos");
            modelo.addColumn("Duelos Aéreos Tentados");
            modelo.addColumn("Duelos Aéreos Ganhos por 90 Minutos");
            modelo.addColumn("Duelos Aéreos Tentados por 90 Minutos");
            modelo.addColumn("Porcentagem de Duelos Aéreos Ganhos");

            java.util.List<Defensor> lista = dao.listarDefensores();
            for (Defensor defensor : lista) {
                modelo.addRow(new Object[]{
                    defensor.getNome(),
                    defensor.getTime(), 
                    defensor.getNacionalidade(),
                    defensor.getIdade(), 
                    defensor.getNumero(), 
                    defensor.getJogos(), 
                    defensor.getJogosTitular(), 
                    defensor.getMinutosJogados(),
                    fmt(defensor.calcularNota()),
                    defensor.getDesarmes(),
                    fmt(defensor.calcularDesarmesPor90()),
                    defensor.getInterceptacoes(),
                    fmt(defensor.calcularInterceptacoesPor90()),
                    defensor.getCortes(),
                    fmt(defensor.calcularBolasRecuperadasPor90()),
                    defensor.getFaltas(),
                    fmt(defensor.calcularFaltasPor90()),
                    defensor.getDuelosGanhos(),
                    defensor.getDuelosTentados(),
                    fmt(defensor.calcularDuelosGanhosPor90()),
                    fmt(defensor.calcularDuelosTentadosPor90()),
                    fmt(defensor.calcularPorcentagemDuelosGanhos()) + "%",
                    defensor.getDuelosaereosGanhos(),
                    defensor.getDuelosaereosTentados(),
                    fmt(defensor.calcularDuelosAereosGanhosPor90()),
                    fmt(defensor.calcularDuelosAereosTentadosPor90()),
                    fmt(defensor.calcularPorcentagemDuelosAereosGanhos()) + "%"
                });
            }
        } 
        else if (posicaoSelecionada.equals("Goleiro")) {
            // --- COLUNAS DO GOLEIRO ---
            modelo.addColumn("Nome");
            modelo.addColumn("Time");
            modelo.addColumn("Nacionalidade");
            modelo.addColumn("Idade");
            modelo.addColumn("Nº");
            modelo.addColumn("Jogos");
            modelo.addColumn("<html><center>Jogos como<br>Titular</center></html>");
            modelo.addColumn("<html><center>Minutos<br>Jogados</center></html>");
            modelo.addColumn("Nota");
            
            modelo.addColumn("Jogos Sem Sofrer Gols (Clean Sheets)");
            modelo.addColumn("Gols Prevenidos");
            modelo.addColumn("Média de Gols Sofridos");
            modelo.addColumn("Porcentagem de Defesas");
            modelo.addColumn("Defesas Difíceis por 90 Minutos");
            modelo.addColumn("Pênaltis Defendidos");

            java.util.List<Goleiro> lista = dao.listarGoleiros();
            for (Goleiro goleiro : lista) {
                modelo.addRow(new Object[]{
                    goleiro.getNome(),
                    goleiro.getTime(), 
                    goleiro.getNacionalidade(),
                    goleiro.getIdade(), 
                    goleiro.getNumero(), 
                    goleiro.getJogos(), 
                    goleiro.getJogosTitular(), 
                    goleiro.getMinutosJogados(),
                    fmt(goleiro.calcularNota()),
                    goleiro.getCleanSheets(),
                    fmt(goleiro.calcularGolsPrevenidos()),
                    fmt(goleiro.calcularMediaGolsSofridos()),
                    fmt(goleiro.calcularPorcentagemDefesas()) + "%",
                    fmt(goleiro.calcularDefesasDificeisPor90()),
                    goleiro.getPenaltisDefendidos()
                });
            }
        }
    } finally {
        dao.fecharConexao();
    }

    // --- CONFIGURAÇÃO DA COLUNA CONGELADA ---

    // 2. Trava a criação automática para não restaurar colunas removidas
    tabelaScout.setAutoCreateColumnsFromModel(false);
    tabelaFixa.setAutoCreateColumnsFromModel(false);

    // 3. Na tabela de rolagem (tabelaScout), remove a coluna 0 ("Nome")
    if (tabelaScout.getColumnCount() > 0) {
        tabelaScout.removeColumn(tabelaScout.getColumnModel().getColumn(0));
    }

    // 4. Na tabela congelada (tabelaFixa), remove todas as colunas A PARTIR do índice 1
    // Deixa exclusivamente a coluna 0 ("Nome")
    while (tabelaFixa.getColumnCount() > 1) {
        tabelaFixa.removeColumn(tabelaFixa.getColumnModel().getColumn(1));
    }

    // 5. Redimensiona as larguras das duas tabelas
    tabelaFixa.setRowHeight(tabelaScout.getRowHeight());
    ajustarLarguraColunas(tabelaFixa);
    ajustarLarguraColunas(tabelaScout);

    // 6. Define a largura da área fixa para bater EXATAMENTE com a largura da coluna "Nome"
    tabelaFixa.setPreferredScrollableViewportSize(tabelaFixa.getPreferredSize());

    // 7. Atualiza o layout
    jScrollPane1.revalidate();
    jScrollPane1.repaint();
    }//GEN-LAST:event_cbPosicaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Filtro telaFiltro = new Filtro();
    telaFiltro.setLocationRelativeTo(null);
    telaFiltro.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbPosicao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaScout;
    // End of variables declaration//GEN-END:variables
}
