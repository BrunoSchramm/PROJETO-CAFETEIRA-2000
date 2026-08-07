package br.com.MODEL;

public class comprador {

private  int id;
private  String nome;
private Double saldo_credito;

public comprador() {
	
}

public comprador(int id, String nome, Double saldo_credito) {
	super();
	this.id = id;
	this.nome = nome;
	this.saldo_credito = saldo_credito;
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

public Double getSaldo_credito() {
	return saldo_credito;
}

public void setSaldo_credito(Double saldo_credito) {
	this.saldo_credito = saldo_credito;
}





}

