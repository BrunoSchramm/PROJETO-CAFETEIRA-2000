package br.com.MODEL;

public class donoDeMaquina {
private int id;
private String nome;
private  boolean usoParaVendas;

public donoDeMaquina() {
}


public donoDeMaquina(int id, String nome, boolean usoParaVendas) {
	super();
	this.id = id;
	this.nome = nome;
	this.usoParaVendas = usoParaVendas;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public boolean isUsoParaVendas() {
	return usoParaVendas;
}


public void setUsoParaVendas(boolean usoParaVendas) {
	this.usoParaVendas = usoParaVendas;
}





}