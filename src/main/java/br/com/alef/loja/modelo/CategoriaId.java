package br.com.alef.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId {
	private String tipo;
	private String nome;
	public CategoriaId(String tipo, String nome) {
		super();
		this.tipo = tipo;
		this.nome = nome;
	}
	public CategoriaId() {
		super();
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
