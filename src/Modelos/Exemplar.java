package Modelos;

import Gestores.GestorLivros;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Exemplar implements Serializable {
    private int codigo;
    private Livro livro;
    private boolean requisitado;
    private LinkedList<Requisicao> requisicoes;

    public Exemplar(Livro livro)
    {
        LinkedList<Integer> codigos = GestorLivros.getInstance().getCodigos();
        do {
            codigo = (new Random()).nextInt(10000);
        } while (codigos.contains(codigo));
        this.livro = livro;
        requisitado = false;
        requisicoes = new LinkedList<>();
    }

    public void devolver()
    {
        requisitado = false;
    }

    public void requisitar(Requisicao requisicao)
    {
        requisitado = true;
        requisicoes.add(requisicao);
    }


    public Livro getLivro() {
        return livro;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isRequisitado() {
        return requisitado;
    }
}
