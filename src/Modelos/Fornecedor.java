package Modelos;

import java.io.Serializable;

public class Fornecedor implements Serializable {
    private String nome;
    private int nif;

    public Fornecedor(String nome, int nif) {
        this.nome = nome;
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public int getNif() {
        return nif;
    }

    public void atualizar(String nome, int nif) {
        this.nome = nome;
        this.nif = nif;
    }
}
