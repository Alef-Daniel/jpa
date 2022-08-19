package br.com.alef.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alef.loja.modelo.Pedido;
import br.com.alef.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return em.find(Pedido.class, id);

	}

	public List<Pedido> buscarTodos() {

		String jpql = "SELECT p FROM Pedido p";

		return em.createQuery(jpql, Pedido.class).getResultList();

	}

	public List<Pedido> buscarPorNome(String nome) {

		String jpql = "SELECT p FROM Pedido p where p.nome= :nome";

		return em.createQuery(jpql, Pedido.class).setParameter("nome", nome).getResultList();

	}
	
	public List<Pedido> buscarPorNomeDaCategoria(String nome) {

		String jpql = "SELECT p FROM Pedido p where p.categoria.nome= :nome";

		return em.createQuery(jpql, Pedido.class)
				.setParameter("nome", nome)
				.getResultList();

	}
	
	public BigDecimal buscarPrecoDoPedidoPorNome(String nome) {

		String jpql = "SELECT p.preco FROM Pedido p where p.nome= :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();

	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
		
	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas(){
		String jpql= "SELECT new br.com.alef.loja.vo.RelatorioDeVendasVo( produto.nome"
				+ ", SUM(item.quantidade),"
				+ "MAX(pedido.data))"
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP by produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}
	
	
	
	
	

}
