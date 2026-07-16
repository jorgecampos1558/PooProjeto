package com.mycompany.pooprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JogadorDAO {

    private Connection conexao;

    public JogadorDAO() {
        conexao = Conexao.conectar();
    }

    // ==========================
    // ATACANTE
    // ==========================

    public Atacante buscarAtacante(String nome) {

        String sql = """
            SELECT *
            FROM jogadores j
            INNER JOIN atacante_stats a
            ON j.id = a.jogador_id
            WHERE j.nome = ?
            """;

        try {

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Atacante(

                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),

                        rs.getInt("gols"),
                        rs.getInt("assistencias"),
                        rs.getInt("finalizacoes"),
                        rs.getInt("finalizacoes_no_gol"),
                        rs.getInt("dribles_certos"),
                        rs.getInt("perdas_de_posse")
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    // ==========================
    // MEIO CAMPISTA
    // ==========================

    public MeioCampista buscarMeioCampista(String nome) {

        String sql = """
            SELECT *
            FROM jogadores j
            INNER JOIN meio_stats m
            ON j.id = m.jogador_id
            WHERE j.nome = ?
            """;

        try {

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new MeioCampista(

                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),

                        rs.getInt("assistencias"),
                        rs.getInt("passes_certos"),
                        rs.getInt("passes_tentados"),
                        rs.getInt("passes_decisivos"),
                        rs.getInt("chances_criadas"),
                        rs.getInt("desarmes"),
                        rs.getInt("interceptacoes")
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    
    // ==========================
    // DEFENSOR
    // ==========================

    public Defensor buscarDefensor(String nome) {

        String sql = """
            SELECT *
            FROM jogadores j
            INNER JOIN defensor_stats d
            ON j.id = d.jogador_id
            WHERE j.nome = ?
            """;

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Defensor(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),

                        rs.getInt("desarmes"),
                        rs.getInt("interceptacoes"),
                        rs.getInt("cortes"),
                        rs.getInt("duelos_ganhos"),
                        rs.getInt("duelos_tentados"),
                        rs.getInt("faltas")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ==========================
    // GOLEIRO
    // ==========================

   public Goleiro buscarGoleiro(String nome) {
        String sql = """
            SELECT *
            FROM jogadores j
            INNER JOIN goleiro_stats g
            ON j.id = g.jogador_id
            WHERE j.nome = ?
            """;

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Goleiro(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),
                        
                        rs.getInt("defesas"),
                        rs.getInt("defesas_dificeis"),
                        rs.getInt("gols_sofridos"),
                        rs.getDouble("xg_contra"),
                        rs.getInt("clean_sheets"),
                        rs.getInt("penaltis_defendidos")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void fecharConexao() {

        try {

            conexao.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}