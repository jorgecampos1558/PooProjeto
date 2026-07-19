package com.mycompany.pooprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JogadorDAO {

    private Connection conexao;

    public JogadorDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/scoutpro";
            String user = "root";
            String password = "123456";
            this.conexao = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Atacante buscarAtacante(String nomeCompleto) {
        String sql = "SELECT j.*, a.gols, a.assistencias, a.finalizacoes, a.finalizacoes_no_gol, a.dribles_certos, a.perdas_de_posse, a.xG, a.grandes_chances_perdidas FROM jogadores j JOIN atacante_stats a ON j.id = a.jogador_id WHERE j.nome LIKE ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + nomeCompleto + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                return new Atacante(
                    rs.getString("nome"), 
                    rs.getInt("idade"), 
                    rs.getString("posicao"),
                    rs.getInt("numero"),
                    rs.getInt("jogos"), 
                    rs.getInt("jogos_titular"), 
                    rs.getInt("minutos_jogados"),
                    rs.getString("time"),
                    rs.getInt("gols"), 
                    rs.getInt("assistencias"), 
                    rs.getInt("finalizacoes"),
                    rs.getInt("finalizacoes_no_gol"), 
                    rs.getInt("dribles_certos"),
                    rs.getInt("perdas_de_posse"), 
                    rs.getDouble("xG"), 
                    rs.getInt("grandes_chances_perdidas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MeioCampista buscarMeioCampista(String nomeCompleto) {
        String sql = "SELECT j.*, m.assistencias, m.passes_certos, m.passes_tentados, m.passes_decisivos, m.chances_criadas, m.desarmes, m.interceptacoes, m.bolas_recuperadas, m.perdas_de_posse FROM jogadores j JOIN meio_stats m ON j.id = m.jogador_id WHERE j.nome LIKE ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + nomeCompleto + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                return new MeioCampista(
                    rs.getString("nome"), 
                    rs.getInt("idade"), 
                    rs.getString("posicao"),
                    rs.getInt("numero"),
                    rs.getInt("jogos"), 
                    rs.getInt("jogos_titular"), 
                    rs.getInt("minutos_jogados"),
                    rs.getString("time"),
                    rs.getInt("assistencias"), 
                    rs.getInt("passes_certos"), 
                    rs.getInt("passes_tentados"),
                    rs.getInt("passes_decisivos"), 
                    rs.getInt("chances_criadas"), 
                    rs.getInt("desarmes"),
                    rs.getInt("interceptacoes"), 
                    rs.getInt("bolas_recuperadas"),
                    rs.getInt("perdas_de_posse") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Defensor buscarDefensor(String nomeCompleto) {
        String sql = "SELECT j.*, d.desarmes, d.interceptacoes, d.cortes, d.duelos_ganhos, d.duelos_tentados, d.faltas, d.duelosaereos_ganhos, d.duelosaereos_tentados, d.bolas_recuperadas FROM jogadores j JOIN defensor_stats d ON j.id = d.jogador_id WHERE j.nome LIKE ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + nomeCompleto + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                return new Defensor(
                    rs.getString("nome"),
                    rs.getInt("id"),
                    rs.getInt("idade"),
                    rs.getString("posicao"),
                    rs.getInt("numero"),
                    rs.getInt("jogos"),
                    rs.getInt("jogos_titular"),
                    rs.getInt("minutos_jogados"),
                    rs.getString("time"),
                    rs.getInt("desarmes"),
                    rs.getInt("interceptacoes"),
                    rs.getInt("cortes"),
                    rs.getInt("duelos_ganhos"),
                    rs.getInt("duelos_tentados"),
                    rs.getInt("faltas"),
                    rs.getInt("duelosaereos_ganhos"),
                    rs.getInt("duelosaereos_tentados"),
                    rs.getInt("bolas_recuperadas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Goleiro buscarGoleiro(String nomeCompleto) {
        String sql = "SELECT j.*, g.defesas, g.defesas_dificeis, g.gols_sofridos, g.xg_contra, g.clean_sheets, g.penaltis_defendidos FROM jogadores j JOIN goleiro_stats g ON g.jogador_id = j.id WHERE j.nome LIKE ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + nomeCompleto + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                return new Goleiro(
                    rs.getString("nome"), 
                    rs.getInt("idade"), 
                    rs.getString("posicao"),
                    rs.getInt("numero"),
                    rs.getInt("jogos"), 
                    rs.getInt("jogos_titular"), 
                    rs.getInt("minutos_jogados"),
                    rs.getString("time"),
                    rs.getInt("defesas"), 
                    rs.getInt("defesas_dificeis"), 
                    rs.getInt("gols_sofridos"),
                    rs.getDouble("xg_contra"), 
                    rs.getInt("clean_sheets"), 
                    rs.getInt("penaltis_defendidos")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}