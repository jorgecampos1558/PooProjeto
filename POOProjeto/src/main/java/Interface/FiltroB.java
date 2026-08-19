package Interface;

public class FiltroB {

    private String tipoNacionalidade;
    private String nacionalidade;

    private Integer idadeMin;
    private Integer idadeMax;

    private String time; 

    private String tipoMinutos;
    private Integer valorMinutos;

    private String tipoNota;
    private Double valorNota;

    private String posicao;
    private String colunaOrdenacao;
    private String tipoOrdem;

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

    public FiltroB() {
    }

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