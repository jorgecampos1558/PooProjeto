package Posições;

public abstract class Jogador {

    protected String nome;
    protected int idade;
    protected String posicao;
    protected int numero;
    protected int jogos;
    protected int jogosTitular;
    protected int minutosJogados;
    protected String time;
    protected String nacionalidade;

    public Jogador(String nome, int idade, String posicao, int numero,
                   int jogos, int jogosTitular, int minutosJogados, String time, String nacionalidade) {

        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.numero = numero;
        this.jogos = jogos;
        this.jogosTitular = jogosTitular;
        this.minutosJogados = minutosJogados;
        this.time = time;
        this.nacionalidade = nacionalidade;
    }

    public abstract double calcularNota();

    // ==========================================
    // GETTERS & SETTERS (Necessários para a TelaPrincipal e Subclasses)
    // ==========================================

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getNumero() {
        return numero;
    }

    public int getJogos() {
        return jogos;
    }

    public int getJogosTitular() {
        return jogosTitular;
    }

    public int getMinutosJogados() {
        return minutosJogados;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}