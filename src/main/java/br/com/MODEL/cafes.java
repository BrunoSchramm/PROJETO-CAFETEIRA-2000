package br.com.MODEL;

public class cafes {

private int id;
private String nome;
private Double	preco;
private int	agua_necessaria; 
 private int graos_necessarios;
 
 public cafes() {
	 
 }
 
 public cafes(int id, String nome, Double preco, int agua_necessaria, int graos_necessarios) {
	super();
	this.id = id;
	this.nome = nome;
	this.preco = preco;
	this.agua_necessaria = agua_necessaria;
	this.graos_necessarios = graos_necessarios;
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

 public Double getPreco() {
	return preco;
 }

 public void setPreco(Double preco) {
	this.preco = preco;
 }

 public int getAgua_necessaria() {
	return agua_necessaria;
 }

 public void setAgua_necessaria(int agua_necessaria) {
	this.agua_necessaria = agua_necessaria;
 }

 public int getGraos_necessarios() {
	return graos_necessarios;
 }

 public void setGraos_necessarios(int graos_necessarios) {
	this.graos_necessarios = graos_necessarios;
 } 
	
	
	
}