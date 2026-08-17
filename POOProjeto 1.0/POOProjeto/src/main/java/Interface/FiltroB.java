package Interface;

/**
 * Objeto de Transferência de Dados (DTO) para transportar
 * os critérios selecionados na tela de Filtros.
 */
public class FiltroB {

    // 1. Filtro de Nacionalidade
    private String tipoNacionalidade;
    private String nacionalidade;
    
    // 2. Faixa de Idade
    private Integer idadeMin;
    private Integer idadeMax;

    // 3. Clube / Time
    private String time; 

    // 4. Minutos Jogados
    private String tipoMinutos;
    private Integer valorMinutos;

    // 5. Nota Média
    private String tipoNota;
    private Double valorNota;

    // 6. Ordenação e Posição (Guias)
    private String posicao;         // Ex: "Atacantes", "Meio-Campistas", "Defensores", "Goleiros"
    private String colunaOrdenacao; // Ex: "Gols", "Assistências", "Defesas", "Nota Média"
    private String tipoOrdem;       // Ex: "Crescente", "Decrescente"

    // Construtor completo com todos os parâmetros
    public FiltroB(String tipoNacionalidade, String nacionalidade, Integer idadeMin, Integer idadeMax,
                   String time, String tipoMinutos, Integer valorMinutos, String tipoNota, Double valorNota,
                   String posicao, String colunaOrdenacao, String tipoOrdem) {
        this.tipoNacionalidade = tipoNacionalidade;
        this.nacionalidade = nacionalidade;
        this.idadeMin = idadeMin;
        this.idadeMax = idadeMax;
        this.time = time;
        this.tipoMinutos = tipoMinutos;
        this.valorMinutos = valorMinutos;
        this.tipoNota = tipoNota;
        this.valorNota = valorNota;
        this.posicao = posicao;
        this.colunaOrdenacao = colunaOrdenacao;
        this.tipoOrdem = tipoOrdem;
    }

    // Construtor padrão (vazio)
    public FiltroB() {
    }

    // ==========================================
    // GETTERS E SETTERS
    // ==========================================

    public String getTipoNacionalidade() {
        return tipoNacionalidade;
    }

    public void setTipoNacionalidade(String tipoNacionalidade) {
        this.tipoNacionalidade = tipoNacionalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Integer getIdadeMin() {
        return idadeMin;
    }

    public void setIdadeMin(Integer idadeMin) {
        this.idadeMin = idadeMin;
    }

    public Integer getIdadeMax() {
        return idadeMax;
    }

    public void setIdadeMax(Integer idadeMax) {
        this.idadeMax = idadeMax;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTipoMinutos() {
        return tipoMinutos;
    }

    public void setTipoMinutos(String tipoMinutos) {
        this.tipoMinutos = tipoMinutos;
    }

    public Integer getValorMinutos() {
        return valorMinutos;
    }

    public void setValorMinutos(Integer valorMinutos) {
        this.valorMinutos = valorMinutos;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    public Double getValorNota() {
        return valorNota;
    }

    public void setValorNota(Double valorNota) {
        this.valorNota = valorNota;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getColunaOrdenacao() {
        return colunaOrdenacao;
    }

    public void setColunaOrdenacao(String colunaOrdenacao) {
        this.colunaOrdenacao = colunaOrdenacao;
    }

    public String getTipoOrdem() {
        return tipoOrdem;
    }

    public void setTipoOrdem(String tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }
}