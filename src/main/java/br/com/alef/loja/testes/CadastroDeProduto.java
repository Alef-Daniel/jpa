package br.com.alef.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

import br.com.alef.loja.dao.CategoriaDao;
import br.com.alef.loja.dao.ProdutoDao;
import br.com.alef.loja.modelo.Categoria;
import br.com.alef.loja.modelo.Produto;
import br.com.alef.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		
		cadastrarProduto();
		
		
	
		
		EntityManager em = JPAUtil.getEntitymanager();
		ProdutoDao pdao = new ProdutoDao(em);
		
		Produto produto= pdao.buscarPorId(1l);
		System.out.println(produto.getPreco());
		List<Produto> produtos = pdao.buscarPorNomeDaCategoria("Celulares");
		produtos.forEach(p2 -> System.out.println(p2.getNome()));
		
		
		BigDecimal precoProduto = pdao.buscarPrecoDoProdutoPorNome("Iphone");
		System.out.println(precoProduto);
		
		pdao.buscarPorParametrosComCriteria("iphone", null,null);
		
		
	}

	private static void cadastrarProduto() {
		Categoria categoria= new Categoria("Celulares");
		
		Produto produto = new Produto("IPHONE","AZUL",new BigDecimal("800"), categoria);
		
		
		EntityManager em = JPAUtil.getEntitymanager();
		ProdutoDao pdao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		em.getTransaction().begin();
		categoriaDao.cadastrar(categoria);
		pdao.cadastrar(produto);
		em.getTransaction().commit();
		em.close();
	}

}
