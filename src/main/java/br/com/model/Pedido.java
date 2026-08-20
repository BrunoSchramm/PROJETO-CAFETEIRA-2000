package br.com.model;

import java.time.LocalDateTime;

public class Pedido {
    
    private int id;
    private int idCafeteira;
    private int idCafe;
    private int idComprador;
    private LocalDateTime dataHora;
    
    public Pedido() {
    }
    
    public Pedido(int id, int idCafeteira, int idCafe, int idComprador, LocalDateTime dataHora) {
        this.id = id;
        this.idCafeteira = idCafeteira;
        this.idCafe = idCafe;
        this.idComprador = idComprador;
        this.dataHora = dataHora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCafeteira() { return idCafeteira; }
    public void setIdCafeteira(int idCafeteira) { this.idCafeteira = idCafeteira; }

    public int getIdCafe() { return idCafe; }
    public void setIdCafe(int idCafe) { this.idCafe = idCafe; }

    public int getIdComprador() { return idComprador; }
    public void setIdComprador(int idComprador) { this.idComprador = idComprador; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; } 
}