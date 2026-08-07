package br.com.MODEL;

import java.time.LocalDateTime;

public class pedidos {
	
	private int id;
	private int id_cafeteira;
	private int id_cafe;
	private int id_comprador;
	private LocalDateTime data_hora;
	
	public pedidos() {
		
	}
	
	public pedidos(int id, int id_cafeteira, int id_cafe, int id_comprador, LocalDateTime data_hora) {
		super();
		this.id = id;
		this.id_cafeteira = id_cafeteira;
		this.id_cafe = id_cafe;
		this.id_comprador = id_comprador;
		this.data_hora = data_hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cafeteira() {
		return id_cafeteira;
	}

	public void setId_cafeteira(int id_cafeteira) {
		this.id_cafeteira = id_cafeteira;
	}

	public int getId_cafe() {
		return id_cafe;
	}

	public void setId_cafe(int id_cafe) {
		this.id_cafe = id_cafe;
	}

	public int getId_comprador() {
		return id_comprador;
	}

	public void setId_comprador(int id_comprador) {
		this.id_comprador = id_comprador;
	}

	public LocalDateTime getData_hora() {
		return data_hora;
	}

	public void setData_hora(LocalDateTime data_hora) {
		this.data_hora = data_hora;
	} 
	
	
	
	
}