package DAO;

import Posições.Atacante;
import Posições.Defensor;
import Posições.Goleiro;
import Posições.MeioCampista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public List<Atacante> listarAtacantes() {
        List<Atacante> lista = new ArrayList<>();
        String sql = "SELECT j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "a.gols, a.assistencias, a.finalizacoes, a.finalizacoes_no_gol, a.dribles_certos, a.perdas_de_posse, a.xG, a.grandes_chances_perdidas " +
                     "FROM jogadores j JOIN atacante_stats a ON j.id = a.jogador_id";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Atacante atacante = new Atacante(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),
                        rs.getString("time"),
                        rs.getString("nacionalidade"),
                        rs.getInt("gols"),
                        rs.getInt("assistencias"),
                        rs.getInt("finalizacoes"),
                        rs.getInt("finalizacoes_no_gol"),
                        rs.getInt("dribles_certos"),
                        rs.getInt("perdas_de_posse"),
                        rs.getDouble("xG"),
                        rs.getInt("grandes_chances_perdidas")
                );
                lista.add(atacante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<MeioCampista> listarMeioCampistas() {
        List<MeioCampista> lista = new ArrayList<>();
        String sql = "SELECT j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "m.assistencias, m.passes_certos, m.passes_tentados, m.passes_decisivos, m.chances_criadas, m.desarmes, m.interceptacoes, m.bolas_recuperadas, m.perdas_de_posse " +
                     "FROM jogadores j JOIN meio_stats m ON j.id = m.jogador_id";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MeioCampista meio = new MeioCampista(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),
                        rs.getString("time"),
                        rs.getString("nacionalidade"),
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
                lista.add(meio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Defensor> listarDefensores() {
        List<Defensor> lista = new ArrayList<>();
        String sql = "SELECT j.id AS j_id, j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "d.desarmes, d.interceptacoes, d.cortes, d.duelos_ganhos, d.duelos_tentados, d.faltas, d.duelosaereos_ganhos, d.duelosaereos_tentados, d.bolas_recuperadas " +
                     "FROM jogadores j JOIN defensor_stats d ON j.id = d.jogador_id";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Defensor defensor = new Defensor(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),
                        rs.getString("time"),
                        rs.getString("nacionalidade"),
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
                lista.add(defensor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Goleiro> listarGoleiros() {
        List<Goleiro> lista = new ArrayList<>();
        String sql = "SELECT j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "g.defesas, g.gols_sofridos, g.xg_contra, g.clean_sheets, g.penaltis_defendidos " +
                     "FROM jogadores j JOIN goleiro_stats g ON g.jogador_id = j.id";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Goleiro goleiro = new Goleiro(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        rs.getInt("numero"),
                        rs.getInt("jogos"),
                        rs.getInt("jogos_titular"),
                        rs.getInt("minutos_jogados"),
                        rs.getString("time"),
                        rs.getString("nacionalidade"),
                        rs.getInt("defesas"),
                        rs.getInt("gols_sofridos"),
                        rs.getDouble("xg_contra"),
                        rs.getInt("clean_sheets"),
                        rs.getInt("penaltis_defendidos")
                );
                lista.add(goleiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public Atacante buscarAtacante(String nomeCompleto) {
        String sql = "SELECT j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "a.gols, a.assistencias, a.finalizacoes, a.finalizacoes_no_gol, a.dribles_certos, a.perdas_de_posse, a.xG, a.grandes_chances_perdidas " +
                     "FROM jogadores j JOIN atacante_stats a ON j.id = a.jogador_id WHERE j.nome LIKE ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, "%" + nomeCompleto + "%");
            try (ResultSet rs = ps.executeQuery()) {
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
                            rs.getString("nacionalidade"),
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MeioCampista buscarMeioCampista(String nomeCompleto) {
        String sql = "SELECT j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "m.assistencias, m.passes_certos, m.passes_tentados, m.passes_decisivos, m.chances_criadas, m.desarmes, m.interceptacoes, m.bolas_recuperadas, m.perdas_de_posse " +
                     "FROM jogadores j JOIN meio_stats m ON j.id = m.jogador_id WHERE j.nome LIKE ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, "%" + nomeCompleto + "%");
            try (ResultSet rs = ps.executeQuery()) {
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
                            rs.getString("nacionalidade"),
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Defensor buscarDefensor(String nomeCompleto) {
        String sql = "SELECT j.id AS j_id, j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "d.desarmes, d.interceptacoes, d.cortes, d.duelos_ganhos, d.duelos_tentados, d.faltas, d.duelosaereos_ganhos, d.duelosaereos_tentados, d.bolas_recuperadas " +
                     "FROM jogadores j JOIN defensor_stats d ON j.id = d.jogador_id WHERE j.nome LIKE ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, "%" + nomeCompleto + "%");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Defensor defensor = new Defensor(
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("posicao"),
                            rs.getInt("numero"),
                            rs.getInt("jogos"),
                            rs.getInt("jogos_titular"),
                            rs.getInt("minutos_jogados"),
                            rs.getString("time"),
                            rs.getString("nacionalidade"),
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
                    return defensor;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Goleiro buscarGoleiro(String nomeCompleto) {
        String sql = "SELECT j.nome, j.idade, j.posicao, j.numero, j.jogos, j.jogos_titular, j.minutos_jogados, j.time, j.nacionalidade, " +
                     "g.defesas, g.gols_sofridos, g.xg_contra, g.clean_sheets, g.penaltis_defendidos " +
                     "FROM jogadores j JOIN goleiro_stats g ON g.jogador_id = j.id WHERE j.nome LIKE ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, "%" + nomeCompleto + "%");
            try (ResultSet rs = ps.executeQuery()) {
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
                            rs.getString("nacionalidade"),
                            rs.getInt("defesas"),
                            rs.getInt("gols_sofridos"),
                            rs.getDouble("xg_contra"),
                            rs.getInt("clean_sheets"),
                            rs.getInt("penaltis_defendidos")
                    );
                }
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