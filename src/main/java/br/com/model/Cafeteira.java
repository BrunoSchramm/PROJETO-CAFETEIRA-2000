package br.com.model;

public class Cafeteira {
    
    private int id;
    private int idDono;
    private int qtdAgua;
    private int qtdGraos; 
    private StatusMaquina status;

    public Cafeteira() {
    }

    public Cafeteira(int id, int idDono, int qtdAgua, int qtdGraos, StatusMaquina status) {
        this.id = id;
        this.idDono = idDono;
        this.qtdAgua = qtdAgua;
        this.qtdGraos = qtdGraos;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdDono() { return idDono; }
    public void setIdDono(int idDono) { this.idDono = idDono; }

    public int getQtdAgua() { return qtdAgua; }
    public void setQtdAgua(int qtdAgua) { this.qtdAgua = qtdAgua; }

    public int getQtdGraos() { return qtdGraos; }
    public void setQtdGraos(int qtdGraos) { this.qtdGraos = qtdGraos; }

    public StatusMaquina getStatus() { return status; }
    public void setStatus(StatusMaquina status) { this.status = status; }
}