package br.com.model;

public class Comprador {

    private int id;
    private String nome;
    private Double saldoCredito;

    public Comprador() {
    }

    public Comprador(int id, String nome, Double saldoCredito) {
        this.id = id;
        this.nome = nome;
        this.saldoCredito = saldoCredito;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getSaldoCredito() { return saldoCredito; }
    public void setSaldoCredito(Double saldoCredito) { this.saldoCredito = saldoCredito; }
}