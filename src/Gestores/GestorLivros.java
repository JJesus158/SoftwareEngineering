package Gestores;

import Modelos.Exemplar;
import Modelos.Livro;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

public class GestorLivros implements Serializable
{

    private final LinkedList<Livro> livros;
    private final LinkedList<Integer> codigos;
    @Serial
    private static final long serialVersionUID = 1L;

    private static GestorLivros instance = null;

    public GestorLivros()
    {
        livros = new LinkedList<>();
        codigos = new LinkedList<>();
    }

    public static GestorLivros getInstance() {
        if(instance == null){
            instance = new GestorLivros();
        }
        return instance;
    }

    public LinkedList<Livro> mostrarTodosLivros()
    {
        return new LinkedList<>(livros);
    }

    public LinkedList<Livro> mostrarDisponiveis()
    {
        LinkedList<Livro> disponiveis = new LinkedList<>();

        for (Livro livro : livros)
        {
            if (livro.isDisponivel())
            {
                disponiveis.add(livro);
            }
        }

        return disponiveis;
    }

    public LinkedList<Livro> pesquisarPorTituloAutor(String texto)
    {
        LinkedList<Livro> pesquisa = new LinkedList<>();

        for (Livro livro : livros)
        {
            if (livro.getTitulo().contains(texto) || livro.getAutores().contains(texto))
            {
                pesquisa.add(livro);
            }
        }

        return pesquisa;
    }

    public LinkedList<Livro> pesquisarDisponiveisPorTituloAutor(String texto)
    {
        LinkedList<Livro> livrosDisponiveis = mostrarDisponiveis();
        LinkedList<Livro> pesquisa = new LinkedList<>();

        for (Livro livro : livrosDisponiveis)
        {
            if (livro.getTitulo().contains(texto) || livro.getAutores().contains(texto))
            {
                pesquisa.add(livro);
            }
        }

        return pesquisa;
    }

    public void inserir(Livro livro)
    {
        for (Livro atual : livros)
        {
            if (livro.getTitulo().compareTo(atual.getTitulo()) == 0)
            {
                JOptionPane.showMessageDialog(null, "Não podem haver livros com titulo repetido");
                return;
            }
        }
        livros.add(livro);
        guardar();
    }

    public void remover(Livro livro)
    {
        livros.remove(livro);
        guardar();
    }

    public LinkedList<Integer> getCodigos() {
        return codigos;
    }

    public void guardar() {
        try (FileOutputStream fileOut = new FileOutputStream("gestor_livros.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Serialized data is saved: gestor_livros.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void setInstance(GestorLivros instance) {
        GestorLivros.instance = instance;
    }

    public void criarExemplar(Livro livro) {
         registarCodigo(livro.criarExemplar());
         guardar();
    }

    public void removerExemplar(Exemplar exemplar, Livro livro)
    {
        removerCodigo(livro.removerExemplar(exemplar));
        guardar();
    }

    private void removerCodigo(int codigo) {
        codigos.remove(Integer.valueOf(codigo));
    }

    private void registarCodigo(int codigo)
    {
        codigos.add(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorLivros that = (GestorLivros) o;
        return Objects.equals(livros, that.livros) && Objects.equals(codigos, that.codigos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livros, codigos);
    }
}
