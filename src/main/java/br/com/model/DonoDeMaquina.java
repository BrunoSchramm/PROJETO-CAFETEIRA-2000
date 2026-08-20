package br.com.model;

public class DonoDeMaquina {

    private int id;
    private String nome;
    private boolean usoParaVendas;

    public DonoDeMaquina() {
    }

    public DonoDeMaquina(int id, String nome, boolean usoParaVendas) {
        this.id = id;
        this.nome = nome;
        this.usoParaVendas = usoParaVendas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isUsoParaVendas() { return usoParaVendas; }
    public void setUsoParaVendas(boolean usoParaVendas) { this.usoParaVendas = usoParaVendas; }
}