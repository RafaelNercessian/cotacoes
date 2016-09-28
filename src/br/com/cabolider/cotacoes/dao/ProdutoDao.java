package br.com.cabolider.cotacoes.dao;

import java.util.List;

import br.com.cabolider.cotacoes.modelo.Produto;

public interface ProdutoDao {
    public void adiciona(Produto var1);

    public Produto busca(String var1);

    public void deleta(Integer var1);

    public void altera(Produto var1);

    public List<Produto> listaTudo();
}