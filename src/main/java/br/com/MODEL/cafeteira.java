package br.com.MODEL;

public class cafeteira {
	
private int	id;
private int	id_dono;
private int qtdAgua;
private int	qtdGraos; 
private	StatusMaquina status;


public cafeteira() {
	
}

public cafeteira(int id, int id_dono, int qtdAgua, int qtdGraos, StatusMaquina status) {
	super();
	this.id = id;
	this.id_dono = id_dono;
	this.qtdAgua = qtdAgua;
	this.qtdGraos = qtdGraos;
	this.status = status;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getId_dono() {
	return id_dono;
}

public void setId_dono(int id_dono) {
	this.id_dono = id_dono;
}

public int getQtdAgua() {
	return qtdAgua;
}

public void setQtdAgua(int qtdAgua) {
	this.qtdAgua = qtdAgua;
}

public int getQtdGraos() {
	return qtdGraos;
}

public void setQtdGraos(int qtdGraos) {
	this.qtdGraos = qtdGraos;
}

public StatusMaquina getStatus() {
	return status;
}

public void setStatus(StatusMaquina status) {
	this.status = status;
}
	
	
	
	
	
}