package Interface;

import java.awt.Dimension;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Filtro extends javax.swing.JFrame {

    private TelaPrincipal telaPrincipal;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Filtro.class.getName());

    public Filtro(TelaPrincipal telaPrincipal) {
        this();
        this.telaPrincipal = telaPrincipal;
    }

    public Filtro() {
        initComponents();
        Dimension tamanhoFixo = new Dimension(45, 22);

        jTextField1.setText("");
        jTextField1.setPreferredSize(tamanhoFixo);
        jTextField1.setMinimumSize(tamanhoFixo);
        jTextField1.setMaximumSize(tamanhoFixo);
        jTextField1.setDocument(new LimiteCaracteres(2));

        jTextField2.setText("");
        jTextField2.setPreferredSize(tamanhoFixo);
        jTextField2.setMinimumSize(tamanhoFixo);
        jTextField2.setMaximumSize(tamanhoFixo);
        jTextField2.setDocument(new LimiteCaracteres(2));

        jTextField3.setText("");
        jTextField3.setColumns(3);
        jTextField3.setPreferredSize(tamanhoFixo);
        jTextField3.setMinimumSize(tamanhoFixo);
        jTextField3.setMaximumSize(tamanhoFixo);
        jTextField3.setDocument(new LimiteCaracteres(4));

        jTextField4.setText("");
        jTextField4.setColumns(3);
        jTextField4.setPreferredSize(tamanhoFixo);
        jTextField4.setMinimumSize(tamanhoFixo);
        jTextField4.setMaximumSize(tamanhoFixo);
        jTextField4.setDocument(new LimiteDecimal(5));
    }

    private static class LimiteDecimal extends PlainDocument {
        private final int limite;

        LimiteDecimal(int limite) {
            this.limite = limite;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            String textoAtual = getText(0, getLength());
            boolean jaTemSeparador = textoAtual.contains(",") || textoAtual.contains(".");
            boolean ehSeparador = str.equals(",") || str.equals(".");

            if (ehSeparador && jaTemSeparador) return;
            if ((getLength() + str.length()) > limite) return;
            if (!str.matches("[0-9,.]+")) return;

            super.insertString(offset, str, attr);
        }
    }

private FiltroB construirFiltro() {
    FiltroB filtro = new FiltroB();

    // 1. Nacionalidade
    if (jComboBox2.getSelectedItem() != null) {
        String pais = jComboBox2.getSelectedItem().toString();
        if (!pais.equalsIgnoreCase("Qualquer")) {
            filtro.setNacionalidade(pais);
            filtro.setTipoNacionalidade(
                jComboBox1.getSelectedItem() != null ? jComboBox1.getSelectedItem().toString() : "É"
            );
        }
    }

    // 2. Faixa de Idade (De / Até)
    try {
        String idadeMinTexto = jTextField2.getText().trim();
        if (!idadeMinTexto.isEmpty()) {
            filtro.setIdadeMin(Integer.parseInt(idadeMinTexto));
        }
    } catch (NumberFormatException e) {
        logger.warning("Valor inválido no campo Idade (De).");
    }

    try {
        String idadeMaxTexto = jTextField1.getText().trim();
        if (!idadeMaxTexto.isEmpty()) {
            filtro.setIdadeMax(Integer.parseInt(idadeMaxTexto));
        }
    } catch (NumberFormatException e) {
        logger.warning("Valor inválido no campo Idade (Até).");
    }

    // 3. Time
    if (jComboBox3.getSelectedItem() != null) {
        String time = jComboBox3.getSelectedItem().toString();
        if (!time.equalsIgnoreCase("Qualquer")) {
            filtro.setTime(time);
        }
    }

    // 4. Minutos Jogados
    if (jComboBox4.getSelectedItem() != null) {
        String tipoMinutos = jComboBox4.getSelectedItem().toString();
        if (!tipoMinutos.isBlank() && !tipoMinutos.equalsIgnoreCase("Qualquer")) {
            try {
                String minutosTexto = jTextField3.getText().trim();
                if (!minutosTexto.isEmpty()) {
                    filtro.setTipoMinutos(tipoMinutos);
                    filtro.setValorMinutos(Integer.parseInt(minutosTexto));
                }
            } catch (NumberFormatException e) {
                logger.warning("Valor inválido no campo Minutos Jogados.");
            }
        }
    }

    // 5. Nota Média
    if (jComboBox5.getSelectedItem() != null) {
        String tipoNota = jComboBox5.getSelectedItem().toString();
        if (!tipoNota.isBlank() && !tipoNota.equalsIgnoreCase("Qualquer")) {
            try {
                String notaTexto = jTextField4.getText().trim().replace(",", ".");
                if (!notaTexto.isEmpty()) {
                    filtro.setTipoNota(tipoNota);
                    filtro.setValorNota(Double.parseDouble(notaTexto));
                }
            } catch (NumberFormatException e) {
                logger.warning("Valor inválido no campo Nota Média.");
            }
        }
    }

    String[] posicoesPorAba = {"Atacante", "Meio-Campista", "Defensor", "Goleiro"};
    int abaSelecionada = jTabbedPane1.getSelectedIndex();
    if (abaSelecionada >= 0 && abaSelecionada < posicoesPorAba.length) {
        filtro.setPosicao(posicoesPorAba[abaSelecionada]);
    }

    java.util.Map<javax.swing.JComboBox<String>, String> mapaColunas = new java.util.LinkedHashMap<>();

    if (abaSelecionada == 0) { // Aba Atacantes
        mapaColunas.put(jComboBox7,  "gols");
        mapaColunas.put(jComboBox8,  "xg");
        mapaColunas.put(jComboBox9,  "gols_90");
        mapaColunas.put(jComboBox10, "xg_90");
        mapaColunas.put(jComboBox11, "conversao_gols");
        mapaColunas.put(jComboBox12, "finalizacoes");
        mapaColunas.put(jComboBox13, "finalizacoes_gol");
        mapaColunas.put(jComboBox14, "finalizacoes_90");
        mapaColunas.put(jComboBox15, "precisao_finalizacao");
        mapaColunas.put(jComboBox24, "dribles_certos");
        mapaColunas.put(jComboBox25, "dribles_certos_90");
        mapaColunas.put(jComboBox22, "perda_posse");
        mapaColunas.put(jComboBox23, "perda_posse_90");
        mapaColunas.put(jComboBox21, "participacao_gols_90");
        mapaColunas.put(jComboBox19, "assistencias");
        mapaColunas.put(jComboBox20, "assistencias_90");
        mapaColunas.put(jComboBox17, "chances_perdidas");
        mapaColunas.put(jComboBox18, "chances_perdidas_90");

    } else if (abaSelecionada == 1) { // Aba Meio-Campistas
        mapaColunas.put(jComboBox26, "assistencias");
        mapaColunas.put(jComboBox27, "xa");
        mapaColunas.put(jComboBox28, "assistencias_90");
        mapaColunas.put(jComboBox29, "xa_90");
        mapaColunas.put(jComboBox30, "chances_criadas");
        mapaColunas.put(jComboBox31, "passes_decisivos");
        mapaColunas.put(jComboBox32, "passes_decisivos_90");
        mapaColunas.put(jComboBox33, "passes_certos");
        mapaColunas.put(jComboBox34, "passes_tentados");
        mapaColunas.put(jComboBox35, "precisao_passe");
        mapaColunas.put(jComboBox36, "desarmes");
        mapaColunas.put(jComboBox37, "desarmes_90");
        mapaColunas.put(jComboBox38, "interceptacoes");
        mapaColunas.put(jComboBox39, "interceptacoes_90");
        mapaColunas.put(jComboBox40, "bolas_recuperadas");
        mapaColunas.put(jComboBox41, "bolas_recuperadas_90");
        mapaColunas.put(jComboBox42, "perda_posse");
        mapaColunas.put(jComboBox43, "perda_posse_90");
    }
        else if (abaSelecionada == 2) { // Aba Defensores (ZAG)
        mapaColunas.put(jComboBox44, "desarmes");
        mapaColunas.put(jComboBox45, "desarmes_90");
        mapaColunas.put(jComboBox46, "interceptacoes");
        mapaColunas.put(jComboBox47, "interceptacoes_90");
        mapaColunas.put(jComboBox48, "cortes");
        mapaColunas.put(jComboBox49, "bolas_recuperadas");
        mapaColunas.put(jComboBox50, "bolas_recuperadas_90");
        mapaColunas.put(jComboBox51, "faltas");
        mapaColunas.put(jComboBox52, "faltas_90");
        mapaColunas.put(jComboBox53, "duelos_chao_ganhos");
        mapaColunas.put(jComboBox54, "duelos_chao_tentados");
        mapaColunas.put(jComboBox55, "duelos_chao_ganhos_90");
        mapaColunas.put(jComboBox56, "duelos_chao_tentados_90");
        mapaColunas.put(jComboBox57, "porcentagem_duelos_ganhos");
        mapaColunas.put(jComboBox58, "duelos_aereos_ganhos");
        mapaColunas.put(jComboBox59, "duelos_aereos_tentados");
        mapaColunas.put(jComboBox60, "duelos_aereos_ganhos_90");
        mapaColunas.put(jComboBox61, "duelos_aereos_tentados_90");
        mapaColunas.put(jComboBox62, "porcentagem_duelos_aereos_ganhos");
    }
        else if (abaSelecionada == 3) { // Aba Goleiros (GOL)
        mapaColunas.put(jComboBox63, "clean_sheets");
        mapaColunas.put(jComboBox64, "gols_prevenidos");
        mapaColunas.put(jComboBox65, "media_gols_sofridos");
        mapaColunas.put(jComboBox66, "pct_defesas");
        mapaColunas.put(jComboBox67, "defesas_por_90");
        mapaColunas.put(jComboBox68, "penaltis_defendidos");
        mapaColunas.put(jComboBox69, "gols_sofridos");
        mapaColunas.put(jComboBox70, "xg_contra");
    }

    for (java.util.Map.Entry<javax.swing.JComboBox<String>, String> entry : mapaColunas.entrySet()) {
        if (entry.getKey() != null) {
            Object selecionado = entry.getKey().getSelectedItem();
            if (selecionado != null && !selecionado.toString().equalsIgnoreCase("Qualquer")) {
                filtro.setColunaOrdenacao(entry.getValue());
                filtro.setTipoOrdem(selecionado.toString()); // "Crescente" ou "Decrescente"
                break;
            }
        }
    }

    return filtro;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jComboBox23 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jComboBox24 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jComboBox26 = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jComboBox27 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jComboBox28 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jComboBox29 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jComboBox32 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jComboBox33 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jComboBox34 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jComboBox36 = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        jComboBox37 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jComboBox38 = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jComboBox39 = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jComboBox40 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jComboBox41 = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jComboBox42 = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jComboBox43 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jComboBox44 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jComboBox45 = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jComboBox46 = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jComboBox47 = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jComboBox48 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jComboBox49 = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jComboBox50 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jComboBox51 = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        jComboBox52 = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        jComboBox53 = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        jComboBox54 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jComboBox55 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jComboBox56 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jComboBox57 = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        jComboBox58 = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        jComboBox59 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jComboBox60 = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        jComboBox61 = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jComboBox62 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jComboBox63 = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        jComboBox64 = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        jComboBox65 = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        jComboBox66 = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        jComboBox67 = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        jComboBox68 = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jComboBox69 = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        jComboBox70 = new javax.swing.JComboBox<>();

        jLabel8.setText("jLabel8");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("FILTROS");
        jLabel1.setMaximumSize(new java.awt.Dimension(42, 26));
        jLabel1.setMinimumSize(new java.awt.Dimension(42, 26));
        jLabel1.setPreferredSize(new java.awt.Dimension(42, 26));

        jButton1.setText("Sair");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel2.setText("Nacionalidade");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "É", "Não É" }));
        jComboBox1.setOpaque(true);
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Alemanha", "Angola", "Argentina", "Bélgica", "Bolívia", "Brasil", "Chile", "Colômbia", "Dinamarca", "Equador", "Espanha", "França", "Grécia", "Guiné-Bissau", "Holanda", "Itália", "Panamá", "Paraguai", "Peru", "República Democrática do Congo", "Uruguai", "Venezuela" }));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel4.setText("Até");

        jButton2.setText("Aplicar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel5.setText("Idade");

        jTextField2.addActionListener(this::jTextField2ActionPerformed);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel3.setText("Time");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Athletico-PR", "Atlético-MG", "Bahia", "Botafogo", "Ceará", "Corinthians", "Criciúma", "Cruzeiro", "Flamengo", "Fluminense", "Fortaleza", "Grêmio", "Internacional", "Juventude", "Palmeiras", "Red Bull Bragantino", "Santos", "São Paulo", "Vasco da Gama", "Vitória" }));
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel6.setText("Minutos Jogados");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Maior Que", "Menor Que", " " }));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel7.setText("Nota Média");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Maior Que", "Menor Que", " " }));

        jTextField4.setColumns(3);

        jButton3.setText("Limpar");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jTextField3.setColumns(3);

        jLabel9.setText("Gols");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel10.setText("xG");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel11.setText("Gols/90");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel12.setText("xG/90");

        jLabel13.setText("Conversão em Gols");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel14.setText("Finalizações");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel15.setText("Finalizações no Gol");

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel16.setText("Finalizações/90");

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel17.setText("Precisão de Finalização");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel18.setText("Precisão de Finalização");

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel19.setText("Grandes Chances Perdidas");

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel20.setText("Grandes Chances Perdidas/90");

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel21.setText("Assistências");

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel22.setText("Assistências/90");

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel23.setText("Participação em Gols/90");

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel24.setText("Perda de Posse");

        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel25.setText("Perda de Posse/90");

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel26.setText("Dribles Certos");

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel27.setText("Dribles Certos/90");

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jComboBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel23)
                            .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel22)
                            .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel21)
                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel19)
                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17))))
                    .addComponent(jLabel11))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ATA", jPanel1);

        jLabel28.setText("Assintências");

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel29.setText("Assintências/90");

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel30.setText("xA");

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel31.setText("xA/90");

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel32.setText("Chances Criadas");

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel33.setText("Passes Decisivos");

        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel34.setText("Passes Decisivos/90");

        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel35.setText("Passes Certos");

        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel36.setText("Passes Tentados");

        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel37.setText("Precisão Passes");

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel38.setText("Desarmes");

        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel39.setText("Desarmes/90");

        jComboBox37.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel40.setText("Interceptações");

        jComboBox38.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel41.setText("Interceptações/90");

        jComboBox39.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel42.setText("Bolas Recuperadas");

        jComboBox40.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel43.setText("Bolas Recuperadas/90");

        jComboBox41.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel44.setText("Perdas de Posse");

        jComboBox42.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel45.setText("Perdas de Posse/90");

        jComboBox43.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox40, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox41, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox39, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox38, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox37, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox43, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox42, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(388, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel39)
                    .addComponent(jComboBox37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel40)
                    .addComponent(jComboBox38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel41)
                    .addComponent(jComboBox39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel42)
                    .addComponent(jComboBox40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel43)
                    .addComponent(jComboBox41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel44)
                    .addComponent(jComboBox42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel45)
                    .addComponent(jComboBox43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MC", jPanel2);

        jComboBox44.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel46.setText("Desarmes");

        jLabel47.setText("Desarmes/90");

        jComboBox45.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel48.setText("Interceptações");

        jComboBox46.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel49.setText("Interceptações/90");

        jComboBox47.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel50.setText("Cortes");

        jComboBox48.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel51.setText("Bolas Recuperadas");

        jComboBox49.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel52.setText("Bolas Recuperadas/90");

        jComboBox50.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel53.setText("Faltas");

        jComboBox51.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel54.setText("Faltas/90");

        jComboBox52.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));
        jComboBox52.addActionListener(this::jComboBox52ActionPerformed);

        jLabel56.setText("Duelos no Chão Ganhos");

        jComboBox53.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel57.setText("Duelos no Chão Tentados");

        jComboBox54.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel58.setText("Duelos no Chão Ganhos/90");

        jComboBox55.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel59.setText("Duelos no Chão Tentados/90");

        jComboBox56.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel60.setText("Porcentagem de Duelos Ganhos");

        jComboBox57.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel61.setText("Duelos Aéreos Ganhos");

        jComboBox58.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel62.setText("Duelos Aéreos Tentados");

        jComboBox59.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel63.setText("Duelos Aéreos Ganhos/90");

        jComboBox60.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));
        jComboBox60.addActionListener(this::jComboBox60ActionPerformed);

        jLabel64.setText("Duelos Aéreos Tentados/90");

        jComboBox61.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));
        jComboBox61.addActionListener(this::jComboBox61ActionPerformed);

        jLabel65.setText("Porcentagem de Duelos Aéreos Ganhos");

        jComboBox62.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox50, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox44, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox45, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox46, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox47, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox48, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox49, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox51, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox52, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox62, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox59, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox61, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox58, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox57, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox56, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox55, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox54, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox53, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox60, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jComboBox44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(jComboBox53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jComboBox45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jComboBox54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel58)
                        .addComponent(jComboBox55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jComboBox47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(jComboBox56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jComboBox48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(jComboBox57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jComboBox49, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61)
                    .addComponent(jComboBox58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jComboBox50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(jComboBox60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jComboBox51, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(jComboBox61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jComboBox52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64)
                    .addComponent(jComboBox59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jComboBox62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ZAG", jPanel3);

        jLabel55.setText("Jogos Sem Sofrer Gols");

        jComboBox63.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel66.setText("Gols Prevenidos");

        jComboBox64.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel67.setText("Média de Gols Sofridos");

        jComboBox65.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel68.setText("Porcentagem de Defesas");

        jComboBox66.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel69.setText("Defesas/90");

        jComboBox67.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel70.setText("Pênaltis Defendidos");

        jComboBox68.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel71.setText("Gols Sofridos");

        jComboBox69.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        jLabel72.setText("xG Contra");

        jComboBox70.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qualquer", "Crescente", "Decrescente" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox63, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox64, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox65, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox66, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox67, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox68, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox69, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox70, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(579, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jComboBox63, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jComboBox64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox65, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jComboBox69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox70, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("GOL", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(495, 495, 495)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FiltroB filtro = construirFiltro();

    if (telaPrincipal != null) {
        telaPrincipal.aplicarFiltro(filtro);
    } else {
        logger.warning("Filtro aplicado sem uma TelaPrincipal associada — nada foi atualizado.");
    }

    this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox52ActionPerformed

    private void jComboBox60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox60ActionPerformed

    private void jComboBox61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox61ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Filtro().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox23;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JComboBox<String> jComboBox26;
    private javax.swing.JComboBox<String> jComboBox27;
    private javax.swing.JComboBox<String> jComboBox28;
    private javax.swing.JComboBox<String> jComboBox29;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox31;
    private javax.swing.JComboBox<String> jComboBox32;
    private javax.swing.JComboBox<String> jComboBox33;
    private javax.swing.JComboBox<String> jComboBox34;
    private javax.swing.JComboBox<String> jComboBox35;
    private javax.swing.JComboBox<String> jComboBox36;
    private javax.swing.JComboBox<String> jComboBox37;
    private javax.swing.JComboBox<String> jComboBox38;
    private javax.swing.JComboBox<String> jComboBox39;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox40;
    private javax.swing.JComboBox<String> jComboBox41;
    private javax.swing.JComboBox<String> jComboBox42;
    private javax.swing.JComboBox<String> jComboBox43;
    private javax.swing.JComboBox<String> jComboBox44;
    private javax.swing.JComboBox<String> jComboBox45;
    private javax.swing.JComboBox<String> jComboBox46;
    private javax.swing.JComboBox<String> jComboBox47;
    private javax.swing.JComboBox<String> jComboBox48;
    private javax.swing.JComboBox<String> jComboBox49;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox50;
    private javax.swing.JComboBox<String> jComboBox51;
    private javax.swing.JComboBox<String> jComboBox52;
    private javax.swing.JComboBox<String> jComboBox53;
    private javax.swing.JComboBox<String> jComboBox54;
    private javax.swing.JComboBox<String> jComboBox55;
    private javax.swing.JComboBox<String> jComboBox56;
    private javax.swing.JComboBox<String> jComboBox57;
    private javax.swing.JComboBox<String> jComboBox58;
    private javax.swing.JComboBox<String> jComboBox59;
    private javax.swing.JComboBox<String> jComboBox60;
    private javax.swing.JComboBox<String> jComboBox61;
    private javax.swing.JComboBox<String> jComboBox62;
    private javax.swing.JComboBox<String> jComboBox63;
    private javax.swing.JComboBox<String> jComboBox64;
    private javax.swing.JComboBox<String> jComboBox65;
    private javax.swing.JComboBox<String> jComboBox66;
    private javax.swing.JComboBox<String> jComboBox67;
    private javax.swing.JComboBox<String> jComboBox68;
    private javax.swing.JComboBox<String> jComboBox69;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox70;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
