/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interface;

import Posições.Defensor;
import Posições.MeioCampista;
import Posições.Goleiro;
import Posições.Atacante;
import DAO.JogadorDAO;
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
    private FiltroB filtroAtual = null;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();

        DefaultTableModel modelNaoEditavel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaScout.setModel(modelNaoEditavel);

        tabelaFixa = new javax.swing.JTable(modelNaoEditavel);
        tabelaFixa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabelaFixa.setSelectionModel(tabelaScout.getSelectionModel());
        tabelaFixa.setFocusable(false);
        tabelaFixa.setShowGrid(true);

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

    public void aplicarFiltro(FiltroB filtro) {
        this.filtroAtual = filtro;
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

    private void ordenarAtacantes(java.util.List<Atacante> lista, FiltroB filtro) {
        if (filtro == null || filtro.getColunaOrdenacao() == null || filtro.getTipoOrdem() == null) {
            return;
        }

        java.util.Comparator<Atacante> comparador = switch (filtro.getColunaOrdenacao()) {
            case "gols" ->
                java.util.Comparator.comparingInt(Atacante::getGols);
            case "xg" ->
                java.util.Comparator.comparingDouble(Atacante::getXg);
            case "gols_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularGolsPor90);
            case "xg_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularXGpor90);
            case "conversao_gols" ->
                java.util.Comparator.comparingDouble(Atacante::calcularConversaoGols);
            case "finalizacoes" ->
                java.util.Comparator.comparingInt(Atacante::getFinalizacoes);
            case "finalizacoes_gol" ->
                java.util.Comparator.comparingInt(Atacante::getFinalizacoesNoGol);
            case "finalizacoes_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularFinalizacoesPor90);
            case "precisao_finalizacao" ->
                java.util.Comparator.comparingDouble(Atacante::calcularPrecisaoFinalizacao);
            case "dribles_certos" ->
                java.util.Comparator.comparingDouble(Atacante::getDriblesCertos);
            case "dribles_certos_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularDriblesPor90);
            case "perda_posse" ->
                java.util.Comparator.comparingInt(Atacante::getPerdasDePosse);
            case "perda_posse_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularPerdasPossePor90);
            case "participacao_gols_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularParticipacaoGolsPor90);
            case "assistencias" ->
                java.util.Comparator.comparingInt(Atacante::getAssistencias);
            case "assistencias_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularAssistenciasPor90);
            case "chances_perdidas" ->
                java.util.Comparator.comparingInt(Atacante::getGrandesChancesPerdidas);
            case "chances_perdidas_90" ->
                java.util.Comparator.comparingDouble(Atacante::calcularGrandesChancesPerdidasPor90);
            default ->
                null;
        };

        if (comparador == null) {
            return;
        }

        if (filtro.getTipoOrdem().equalsIgnoreCase("Decrescente")) {
            comparador = comparador.reversed();
        }

        lista.sort(comparador);
    }

    private void ordenarMeioCampistas(java.util.List<MeioCampista> lista, FiltroB filtro) {
        if (filtro == null || filtro.getColunaOrdenacao() == null || filtro.getTipoOrdem() == null) {
            return;
        }

        java.util.Comparator<MeioCampista> comparador = switch (filtro.getColunaOrdenacao()) {
            case "assistencias" ->
                java.util.Comparator.comparingInt(MeioCampista::getAssistencias);
            case "xa" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularxA);
            case "assistencias_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularAssistenciasPor90);
            case "xa_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularxApor90);
            case "passes_decisivos_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularPassesDecisivosPor90);
            case "precisao_passe" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularPrecisaoPasse);
            case "desarmes_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularDesarmesPor90);
            case "interceptacoes_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularInterceptacoesPor90);
            case "bolas_recuperadas_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularBolasRecuperadasPor90);
            case "perda_posse_90" ->
                java.util.Comparator.comparingDouble(MeioCampista::calcularPerdasPossePor90);
            default ->
                null;
        };

        if (comparador == null) {
            return;
        }

        if (filtro.getTipoOrdem().equalsIgnoreCase("Decrescente")) {
            comparador = comparador.reversed();
        }

        lista.sort(comparador);
    }

    private void ordenarDefensores(java.util.List<Defensor> lista, FiltroB filtro) {
        if (filtro == null || filtro.getColunaOrdenacao() == null || filtro.getTipoOrdem() == null) {
            return;
        }

        java.util.Comparator<Defensor> comparador = switch (filtro.getColunaOrdenacao()) {
            case "desarmes" ->
                java.util.Comparator.comparingInt(Defensor::getDesarmes);
            case "desarmes_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularDesarmesPor90);
            case "interceptacoes" ->
                java.util.Comparator.comparingInt(Defensor::getInterceptacoes);
            case "interceptacoes_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularInterceptacoesPor90);
            case "cortes" ->
                java.util.Comparator.comparingInt(Defensor::getCortes);
            case "bolas_recuperadas" ->
                java.util.Comparator.comparingInt(Defensor::getBolasRecuperadas);
            case "bolas_recuperadas_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularBolasRecuperadasPor90);
            case "faltas" ->
                java.util.Comparator.comparingInt(Defensor::getFaltas);
            case "faltas_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularFaltasPor90);
            case "duelos_chao_ganhos" ->
                java.util.Comparator.comparingInt(Defensor::getDuelosGanhos);
            case "duelos_chao_tentados" ->
                java.util.Comparator.comparingInt(Defensor::getDuelosTentados);
            case "duelos_chao_ganhos_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularDuelosGanhosPor90);
            case "duelos_chao_tentados_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularDuelosTentadosPor90);
            case "porcentagem_duelos_ganhos" ->
                java.util.Comparator.comparingDouble(Defensor::calcularPorcentagemDuelosGanhos);
            case "duelos_aereos_ganhos" ->
                java.util.Comparator.comparingInt(Defensor::getDuelosAereosGanhos);
            case "duelos_aereos_tentados" ->
                java.util.Comparator.comparingInt(Defensor::getDuelosAereosTentados);
            case "duelos_aereos_ganhos_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularDuelosAereosGanhosPor90);
            case "duelos_aereos_tentados_90" ->
                java.util.Comparator.comparingDouble(Defensor::calcularDuelosAereosTentadosPor90);
            case "porcentagem_duelos_aereos_ganhos" ->
                java.util.Comparator.comparingDouble(Defensor::calcularPorcentagemDuelosAereosGanhos);
            default ->
                null;
        };

        if (comparador == null) {
            return;
        }

        if (filtro.getTipoOrdem().equalsIgnoreCase("Decrescente")) {
            comparador = comparador.reversed();
        }

        lista.sort(comparador);
    }

   private void ordenarGoleiros(java.util.List<Goleiro> lista, FiltroB filtro) {
    if (filtro == null || filtro.getColunaOrdenacao() == null || filtro.getTipoOrdem() == null || lista == null) {
        return;
    }

    String coluna = filtro.getColunaOrdenacao().toLowerCase().trim();

    java.util.Comparator<Goleiro> comparador = switch (coluna) {
        // Estatísticas específicas de Goleiro
        case "clean_sheets" -> 
            java.util.Comparator.comparingInt(Goleiro::getCleanSheets);
        case "gols_prevenidos" -> 
            java.util.Comparator.comparingDouble(Goleiro::calcularGolsPrevenidos);
        case "media_gols_sofridos" -> 
            java.util.Comparator.comparingDouble(Goleiro::calcularMediaGolsSofridos);
        case "pct_defesas" -> 
            java.util.Comparator.comparingDouble(Goleiro::calcularPorcentagemDefesas); // Corrigido para o método real
        case "defesas_por_90" -> 
            java.util.Comparator.comparingDouble(Goleiro::calcularDefesasPor90);
        case "penaltis_defendidos" -> 
            java.util.Comparator.comparingInt(Goleiro::getPenaltisDefendidos);
        case "defesas" -> 
            java.util.Comparator.comparingInt(Goleiro::getDefesas);
        case "gols_sofridos" -> 
            java.util.Comparator.comparingInt(Goleiro::getGolsSofridos);
        case "xg_contra" -> 
            java.util.Comparator.comparingDouble(Goleiro::getXgContra);
            
        // Dados gerais e de desempenho
        case "jogos" -> 
            java.util.Comparator.comparingInt(Goleiro::getJogos);
        case "minutos_jogados" -> 
            java.util.Comparator.comparingInt(Goleiro::getMinutosJogados);
        case "nota" -> 
            java.util.Comparator.comparingDouble(Goleiro::calcularNota); // Corrigido para o método real
        default -> 
            null;
    };

    if (comparador == null) {
        return;
    }

    if (filtro.getTipoOrdem().equalsIgnoreCase("Decrescente")) {
        comparador = comparador.reversed();
    }

    lista.sort(comparador);
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
            if (headerText.contains("assist") || headerText.contains("xa")
                    || headerText.contains("participaç") || headerText.contains("passe") || headerText.contains("criad")) {

                label.setBackground(new Color(230, 170, 20));
                label.setForeground(Color.BLACK);

            } else if (headerText.contains("gol") || headerText.contains("xg")
                    || headerText.contains("finaliz") || headerText.contains("conversão")
                    || headerText.contains("chance")) {

                label.setBackground(new Color(180, 40, 40));

            } else if (headerText.contains("desarme") || headerText.contains("intercept")
                    || headerText.contains("duelo") || headerText.contains("corte")
                    || headerText.contains("bloque") || headerText.contains("recupera")
                    || headerText.contains("aéreo") || headerText.contains("falta cometi")) {

                label.setBackground(new Color(30, 132, 73)); // Verde Esmeralda

            } else if (headerText.contains("perda") || headerText.contains("posse")) {

                label.setBackground(new Color(52, 152, 219));

            } else if (headerText.contains("clean") || headerText.contains("prevenido")
                    || headerText.contains("defesa") || headerText.contains("pênalti defend")) {

                label.setBackground(new Color(180, 90, 20));

            } else if (headerText.contains("nome") || headerText.contains("time") || headerText.contains("nacionalidade")
                    || headerText.contains("idade") || headerText.contains("nº") || headerText.contains("jogos")
                    || headerText.contains("minutos") || headerText.contains("nota")) {

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
        if (cbPosicao.getSelectedItem() == null) {
            return;
        }

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
                lista = FiltroC.aplicarFiltro(lista, filtroAtual);
                ordenarAtacantes(lista, filtroAtual);

                for (Atacante atacante : lista) {
                    modelo.addRow(new Object[]{
                        atacante.getNome(),
                        atacante.getTime(),
                        atacante.getNacionalidade(),
                        atacante.getIdade(),
                        atacante.getNumero(),
                        atacante.getJogos(),
                        atacante.getJogosTitular(),
                        atacante.getMinutosJogados(),
                        fmt(atacante.calcularNota()),
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
                        atacante.getAssistencias(),
                        fmt(atacante.calcularAssistenciasPor90()),
                        fmt(atacante.calcularParticipacaoGolsPor90()),
                        atacante.getPerdasDePosse(),
                        fmt(atacante.calcularPerdasPossePor90()),
                        atacante.getDriblesCertos(),
                        fmt(atacante.calcularDriblesPor90())
                    });
                }
            } else if (posicaoSelecionada.equals("Meio-Campista")) {
                // Informações Básicas
                modelo.addColumn("Nome");
                modelo.addColumn("Time");
                modelo.addColumn("Nacionalidade");
                modelo.addColumn("Idade");
                modelo.addColumn("Nº");
                modelo.addColumn("Jogos");
                modelo.addColumn("<html><center>Jogos como<br>Titular</center></html>");
                modelo.addColumn("<html><center>Minutos<br>Jogados</center></html>");
                modelo.addColumn("Nota");

                // Métricas de Criação e Passe
                modelo.addColumn("Assistências");
                modelo.addColumn("Assistências Esperadas (xA) Total");
                modelo.addColumn("Assistências por 90 Minutos");
                modelo.addColumn("xA por 90 Minutos");
                modelo.addColumn("Chances Criadas");
                modelo.addColumn("Passes Decisivos");
                modelo.addColumn("Passes Decisivos por 90 Minutos");
                modelo.addColumn("Passes Certos");
                modelo.addColumn("Passes Tentados");
                modelo.addColumn("Precisão de Passe");

                // Métricas Defensivas e de Posse
                modelo.addColumn("Desarmes");
                modelo.addColumn("Desarmes por 90 Minutos");
                modelo.addColumn("Interceptações");
                modelo.addColumn("Interceptações por 90 Minutos");
                modelo.addColumn("Bolas Recuperadas");
                modelo.addColumn("Bolas Recuperadas por 90 Minutos");
                modelo.addColumn("Perdas de Posse");
                modelo.addColumn("Perdas de Posse por 90 Minutos");

                java.util.List<MeioCampista> lista = dao.listarMeioCampistas();
                lista = FiltroC.aplicarFiltro(lista, filtroAtual);
                ordenarMeioCampistas(lista, filtroAtual);

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
                        meio.getChancesCriadas(),
                        meio.getPassesDecisivos(),
                        fmt(meio.calcularPassesDecisivosPor90()),
                        meio.getPassesCertos(),
                        meio.getPassesTentados(),
                        fmt(meio.calcularPrecisaoPasse()) + "%",
                        meio.getDesarmes(),
                        fmt(meio.calcularDesarmesPor90()),
                        meio.getInterceptacoes(),
                        fmt(meio.calcularInterceptacoesPor90()),
                        meio.getBolasRecuperadas(),
                        fmt(meio.calcularBolasRecuperadasPor90()),
                        meio.getPerdasDePosse(),
                        fmt(meio.calcularPerdasPossePor90())
                    });
                }
            } else if (posicaoSelecionada.equals("Defensor")) {
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
                modelo.addColumn("<html><center>Desarmes<br>p/ 90min</center></html>");
                modelo.addColumn("Interceptações");
                modelo.addColumn("<html><center>Interceptações<br>p/ 90min</center></html>");
                modelo.addColumn("Cortes");
                modelo.addColumn("Bolas Recuperadas");
                modelo.addColumn("<html><center>Bolas Recuperadas<br>p/ 90min</center></html>");
                modelo.addColumn("Faltas");
                modelo.addColumn("<html><center>Faltas<br>p/ 90min</center></html>");

                modelo.addColumn("<html><center>Duelos no Chão<br>Ganhos</center></html>");
                modelo.addColumn("<html><center>Duelos no Chão<br>Tentados</center></html>");
                modelo.addColumn("<html><center>Duelos no Chão Ganhos<br>p/ 90min</center></html>");
                modelo.addColumn("<html><center>Duelos no Chão Tentados<br>p/ 90min</center></html>");
                modelo.addColumn("<html><center>% Duelos no Chão<br>Ganhos</center></html>");

                modelo.addColumn("<html><center>Duelos Aéreos<br>Ganhos</center></html>");
                modelo.addColumn("<html><center>Duelos Aéreos<br>Tentados</center></html>");
                modelo.addColumn("<html><center>Duelos Aéreos Ganhos<br>p/ 90min</center></html>");
                modelo.addColumn("<html><center>Duelos Aéreos Tentados<br>p/ 90min</center></html>");
                modelo.addColumn("<html><center>% Duelos Aéreos<br>Ganhos</center></html>");

                java.util.List<Defensor> lista = dao.listarDefensores();
                lista = FiltroC.aplicarFiltro(lista, filtroAtual);
                ordenarDefensores(lista, filtroAtual);

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
                        defensor.getBolasRecuperadas(),
                        fmt(defensor.calcularBolasRecuperadasPor90()),
                        defensor.getFaltas(),
                        fmt(defensor.calcularFaltasPor90()),
                        defensor.getDuelosGanhos(),
                        defensor.getDuelosTentados(),
                        fmt(defensor.calcularDuelosGanhosPor90()),
                        fmt(defensor.calcularDuelosTentadosPor90()),
                        fmt(defensor.calcularPorcentagemDuelosGanhos()) + "%",
                        defensor.getDuelosAereosGanhos(),
                        defensor.getDuelosAereosTentados(),
                        fmt(defensor.calcularDuelosAereosGanhosPor90()),
                        fmt(defensor.calcularDuelosAereosTentadosPor90()),
                        fmt(defensor.calcularPorcentagemDuelosAereosGanhos()) + "%"
                    });
                }
            } else if (posicaoSelecionada.equals("Goleiro")) {
                modelo.addColumn("Nome");
                modelo.addColumn("Time");
                modelo.addColumn("Nacionalidade");
                modelo.addColumn("Idade");
                modelo.addColumn("Nº");
                modelo.addColumn("Jogos");
                modelo.addColumn("<html><center>Jogos como<br>Titular</center></html>");
                modelo.addColumn("<html><center>Minutos<br>Jogados</center></html>");
                modelo.addColumn("Nota");

                // Colunas de Estatísticas Brutas e Métricas de Goleiro
                modelo.addColumn("Defesas");
                modelo.addColumn("Defesas/90");
                modelo.addColumn("Gols Sofridos");
                modelo.addColumn("xG Contra");
                modelo.addColumn("Jogos Sem Sofrer Gols (Clean Sheets)");
                modelo.addColumn("Gols Prevenidos");
                modelo.addColumn("Média de Gols Sofridos");
                modelo.addColumn("Porcentagem de Defesas");
                modelo.addColumn("Pênaltis Defendidos");

               java.util.List<Goleiro> lista = dao.listarGoleiros();
               lista = FiltroC.aplicarFiltro(lista, filtroAtual);
               ordenarGoleiros(lista, filtroAtual);

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
                        // --- ADICIONADOS OS VALORES QUE FALTAVAM ---
                        goleiro.getDefesas(),
                        fmt(goleiro.calcularDefesasPor90()),
                        goleiro.getGolsSofridos(),
                        fmt(goleiro.getXgContra()),
                        // --- DEMAIS MÉTRICAS ---
                        goleiro.getCleanSheets(),
                        fmt(goleiro.calcularGolsPrevenidos()),
                        fmt(goleiro.calcularMediaGolsSofridos()),
                        fmt(goleiro.calcularPorcentagemDefesas()) + "%",
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
        Filtro telaFiltro = new Filtro(this);
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
