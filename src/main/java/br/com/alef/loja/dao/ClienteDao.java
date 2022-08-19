package br.com.alef.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alef.loja.modelo.Cliente;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);

	}

	public List<Cliente> buscarTodos() {

		String jpql = "SELECT p FROM Cliente p";

		return em.createQuery(jpql, Cliente.class).getResultList();

	}

	public List<Cliente> buscarPorNome(String nome) {

		String jpql = "SELECT p FROM Cliente p where p.nome= :nome";

		return em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getResultList();

	}
	
	public List<Cliente> buscarPorNomeDaCategoria(String nome) {

		String jpql = "SELECT p FROM Cliente p where p.categoria.nome= :nome";

		return em.createQuery(jpql, Cliente.class)
				.setParameter("nome", nome)
				.getResultList();

	}
	
	public BigDecimal buscarPrecoDoClientePorNome(String nome) {

		String jpql = "SELECT p.preco FROM Cliente p where p.nome= :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();

	}

}
