package Modelos;

import Gestores.GestorLivros;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Livro implements Serializable {
    private String titulo;
    private String autores;
    private String editora;
    private String isbn;
    private String genero;
    private String subGenero;
    private short quantidadeDisponiveis;
    private short quantidadeTotal;
    private short ano;
    private short sala;
    private short estante;
    private short prateleira;
    private short edicao;
    private Fornecedor fornecedor;
    private LinkedList<Exemplar> exemplares;
    private LinkedList<Reserva> reservas;

    public Livro(String autores, String titulo, String editora, String isbn, String genero, String subGenero, short ano, short sala, short estante, short prateleira, short edicao, Fornecedor fornecedor) {
        this.autores = autores;
        this.titulo = titulo;
        this.editora = editora;
        this.isbn = isbn;
        this.genero = genero;
        this.subGenero = subGenero;
        this.ano = ano;
        this.sala = sala;
        this.estante = estante;
        this.prateleira = prateleira;
        this.edicao = edicao;
        this.fornecedor = fornecedor;
        quantidadeTotal = 0;
        quantidadeDisponiveis = 0;
        reservas = new LinkedList<>();
        exemplares = new LinkedList<>();
    }

    public boolean isDisponivel()
    {
        return quantidadeDisponiveis > 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public String getEditora() {
        return editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenero() {
        return genero;
    }

    public String getSubGenero() {
        return subGenero;
    }

    public short getQuantidadeDisponiveis() {
        return quantidadeDisponiveis;
    }

    public short getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public short getAno() {
        return ano;
    }

    public short getSala() {
        return sala;
    }

    public short getEstante() {
        return estante;
    }

    public short getPrateleira() {
        return prateleira;
    }

    public short getEdicao() {
        return edicao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }

    public LinkedList<Exemplar> getExemplares() {
        return new LinkedList<>(exemplares);
    }

    public void efetuarReserva(Socio socio) {
        Reserva reserva = new Reserva(new Date(), socio, this);
        reservas.add(reserva);
    }

    @Override
    public String toString() {
        return titulo + " - " + autores + " - " + genero + " - Disponivel: " + (isDisponivel() ? "sim" : "nao");
    }

    public void atualizar(String autores, String titulo, String editora, String isbn, String genero, String subgenero, short ano, short sala, short estante, short prateleira, short edicao) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.isbn = isbn;
        this.genero = genero;
        this.subGenero = subgenero;
        this.ano = ano;
        this.sala = sala;
        this.estante = estante;
        this.prateleira = prateleira;
        this.edicao = edicao;
    }

    public int removerExemplar(Exemplar exemplar) {
        exemplares.remove(exemplar);
        quantidadeTotal--;
        quantidadeDisponiveis--;
        return exemplar.getCodigo();
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        GestorLivros.getInstance().guardar();
    }

    public int criarExemplar() {
        Exemplar exemplar = new Exemplar(this);
        exemplares.add(exemplar);
        quantidadeTotal += 1;
        quantidadeDisponiveis += 1;
        return exemplar.getCodigo();
    }
}
