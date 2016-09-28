package br.com.cabolider.cotacoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cabolider.cotacoes.modelo.Produto;

@Repository
public class ProdutoDaoImpl implements ProdutoDao {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public void adiciona(Produto produto) {
		this.manager.persist((Object) produto);
	}

	@Override
	public Produto busca(String codigo) {
		TypedQuery<Produto> query = this.manager.createQuery(
				"select p from Produto p where p.codigo=:codigo",
				Produto.class);
		query.setParameter("codigo", (Object) codigo);
		try {
			return (Produto) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleta(Integer id) {
		Produto produtoASerDeletado = (Produto) this.manager.find(
				Produto.class, (Object) id);
		this.manager.remove((Object) produtoASerDeletado);
	}

	@Transactional
	@Override
	public void altera(Produto produto) {
		this.manager.merge((Object) produto);
	}

	@Override
	public List<Produto> listaTudo() {
		TypedQuery<Produto> query = this.manager.createQuery("select p from Produto p",
				Produto.class);
		return query.getResultList();
	}
}