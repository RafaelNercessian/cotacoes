package br.com.cabolider.cotacoes.modelo;

import java.io.Serializable;

public class ProdutoImpressao
implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer id;
    private String codigo;
    private String descricao;
    private String precoAte1000;
    private String precoAte3000;
    private String precoAcima3000;
    private String ipi;
    private String cor;

    public String getIpi() {
        return this.ipi;
    }

    public void setIpi(String ipi) {
        this.ipi = ipi;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getId() {
        return this.id;
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

    public String getPrecoAte1000() {
        return this.precoAte1000;
    }

    public void setPrecoAte1000(String precoAte1000) {
        this.precoAte1000 = precoAte1000;
    }

    public String getPrecoAte3000() {
        return this.precoAte3000;
    }

    public void setPrecoAte3000(String precoAte3000) {
        this.precoAte3000 = precoAte3000;
    }

    public String getPrecoAcima3000() {
        return this.precoAcima3000;
    }

    public void setPrecoAcima3000(String precoAcima3000) {
        this.precoAcima3000 = precoAcima3000;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.codigo == null ? 0 : this.codigo.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ProdutoImpressao other = (ProdutoImpressao)obj;
        if (this.codigo == null ? other.codigo != null : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }
}
