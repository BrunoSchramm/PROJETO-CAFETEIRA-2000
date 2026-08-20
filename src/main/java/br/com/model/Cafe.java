package br.com.model;

public class Cafe {

    private int id;
    private String nome;
    private Double preco;
    private int aguaNecessaria; 
    private int graosNecessarios;
    
    public Cafe() {
    }
    
    public Cafe(int id, String nome, Double preco, int aguaNecessaria, int graosNecessarios) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.aguaNecessaria = aguaNecessaria;
        this.graosNecessarios = graosNecessarios;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public int getAguaNecessaria() { return aguaNecessaria; }
    public void setAguaNecessaria(int aguaNecessaria) { this.aguaNecessaria = aguaNecessaria; }

    public int getGraosNecessarios() { return graosNecessarios; }
    public void setGraosNecessarios(int graosNecessarios) { this.graosNecessarios = graosNecessarios; } 
}