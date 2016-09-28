package br.com.cabolider.cotacoes.modelo;

public class ProdutoRest {
    private Integer id;
    private String codigo;
    private String descricao;
    private String localizacao;
    private String cor;
    private String tipoDeCobre;
    private String tamanho;
    private Integer saldo;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoDeCobre() {
        return this.tipoDeCobre;
    }

    public void setTipoDeCobre(String tipoDeCobre) {
        this.tipoDeCobre = tipoDeCobre;
    }

    public Integer getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
}