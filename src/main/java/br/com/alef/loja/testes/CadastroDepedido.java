package br.com.alef.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alef.loja.dao.CategoriaDao;
import br.com.alef.loja.dao.ClienteDao;
import br.com.alef.loja.dao.PedidoDao;
import br.com.alef.loja.dao.ProdutoDao;
import br.com.alef.loja.modelo.Categoria;
import br.com.alef.loja.modelo.Cliente;
import br.com.alef.loja.modelo.ItemPedido;
import br.com.alef.loja.modelo.Pedido;
import br.com.alef.loja.modelo.Produto;
import br.com.alef.loja.util.JPAUtil;
import br.com.alef.loja.vo.RelatorioDeVendasVo;

public class CadastroDepedido {
	public static void main(String[] args) {
	popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntitymanager();
		ProdutoDao pdao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		Produto produto = pdao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		em.getTransaction().begin();
		
		Pedido pedido= new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10,pedido,produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		
		

		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		
		System.out.println(totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
		
		
		
		
		
	}
	
	
	
	private static void popularBancoDeDados() {
		Categoria categoria= new Categoria("Celulares");
		
		Produto produto = new Produto("IPHONE","AZUL",new BigDecimal("800"), categoria);
		Cliente cliente= new Cliente("Alef", "123456");
		
		EntityManager em = JPAUtil.getEntitymanager();
		ProdutoDao pdao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(categoria);
		pdao.cadastrar(produto);
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	}

}
