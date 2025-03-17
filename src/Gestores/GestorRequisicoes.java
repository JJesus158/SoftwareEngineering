package Gestores;

import Modelos.Exemplar;
import Modelos.Livro;
import Modelos.Requisicao;
import Enum.EstadoRequisicao;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

public class GestorRequisicoes implements Serializable {

    private final LinkedList<Requisicao> requisicoes;
    @Serial
    private static final long serialVersionUID = 1L;
    private static GestorRequisicoes instance = null;

    public static void setInstance(GestorRequisicoes instance) {
        GestorRequisicoes.instance = instance;
    }

    public GestorRequisicoes()
    {
        requisicoes = new LinkedList<>();
    }

    public static GestorRequisicoes getInstance() {
        if(instance == null){
            instance = new GestorRequisicoes();
        }
        return instance;
    }

    public Set<String> obterTodosAutores() {
        return requisicoes.stream()
                .map(requisicao -> requisicao.getExemplar().getLivro().getAutores())
                .collect(Collectors.toSet());
    }

    public LinkedList<Requisicao> mostrarTodasRequisicoes()
    {
        return new LinkedList<>(requisicoes);
    }

    public void inserir(Requisicao requisicao)
    {

        for (Requisicao atual : requisicoes){
            switch (requisicao.getSocio().getTipo()){
                case ALUNO:
                    if (requisicao.getSocio().getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()){
                        return;
                    }
                    break;
                case PROFESSOR:
                    if (requisicao.getSocio().getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosProfessor()){
                        return;
                    }
                    break;
                case FUNCIONARIO:
                    if (requisicao.getSocio().getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosFuncionario()){
                        return;
                    }
                    break;
            }

        }
        requisicoes.add(requisicao);
    }


    public LinkedList<Requisicao> mostrarRequisicoesPorCodigoDeSocio(String searchTerm) {
        LinkedList<Requisicao> requisicoesPorCodigoDeSocio = new LinkedList<>();
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getSocio().getId() == Integer.parseInt(searchTerm)){
                requisicoesPorCodigoDeSocio.add(requisicao);
            }
        }
        return requisicoesPorCodigoDeSocio;
    }

    public LinkedList<Requisicao> mostrarRequisicoesPorCodigoDeSocioEmAndamento(String searchTerm) {
        LinkedList<Requisicao> requisicoesPorCodigoDeSocioAndamento = new LinkedList<>();
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getSocio().getId() == Integer.parseInt(searchTerm) && requisicao.getEstado() == EstadoRequisicao.EMANDAMENTO){
                requisicoesPorCodigoDeSocioAndamento.add(requisicao);
            }
        }
        return requisicoesPorCodigoDeSocioAndamento;
    }

    public LinkedList<Requisicao> mostrarRequisicoesPorCodigoDeExemplar(String searchTerm) {
        LinkedList<Requisicao> requisicoesPorCodigoDeExemplar = new LinkedList<>();
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getExemplar().getCodigo() == Integer.parseInt(searchTerm)){
                requisicoesPorCodigoDeExemplar.add(requisicao);
            }
        }

        return requisicoesPorCodigoDeExemplar;
    }

    public LinkedList<Requisicao> mostrarRequisicoesPorNomeDoLivro(String searchTerm) {
        LinkedList<Requisicao> requisicoesPorNomeDoLivro = new LinkedList<>();
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getExemplar().getLivro().getTitulo().toLowerCase().contains(searchTerm.toLowerCase())) {
                requisicoesPorNomeDoLivro.add(requisicao);
            }
        }
        return requisicoesPorNomeDoLivro;
    }

    public LinkedList<Requisicao> mostrarRequisicoesPorDataDeRequisicao(String searchTerm) {
        LinkedList<Requisicao> requisicoesPorDataDeRequisicao = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate dateToSearch = LocalDate.parse(searchTerm, formatter);
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getDataInicio().equals(dateToSearch)) {
                requisicoesPorDataDeRequisicao.add(requisicao);
            }
        }
        return requisicoesPorDataDeRequisicao;
    }

    public Requisicao pesquisarrRequisicaoPorExemplarRequisitado(Exemplar exemplar) {

        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getExemplar().equals(exemplar) && !requisicao.getEstado().equals(EstadoRequisicao.PAGO)) {
                return requisicao;
            }
        }
        return null;
    }

    public List<Livro> mostrarTopGeralRequisicoes() {
        Map<Livro, Long> livroCount = requisicoes.stream().collect(Collectors.groupingBy(req -> req.getExemplar().getLivro(), Collectors.counting()));

        return livroCount.entrySet().stream()
                .sorted(Map.Entry.<Livro, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Livro> mostrarTopAutorRequisicoes(String autor) {
        Map<Livro, Long> livroCount = requisicoes.stream()
                .filter(requisicao -> requisicao.getExemplar().getLivro().getAutores().equalsIgnoreCase(autor))
                .collect(Collectors.groupingBy(requisicao -> requisicao.getExemplar().getLivro(), Collectors.counting()));

        return livroCount.entrySet().stream()
                .sorted(Map.Entry.<Livro, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Livro> mostrarTopGeneroRequisicoes(String genero) {
        Map<Livro, Long> livroCount = requisicoes.stream()
                .filter(requisicao -> requisicao.getExemplar().getLivro().getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.groupingBy(requisicao -> requisicao.getExemplar().getLivro(), Collectors.counting()));

        return livroCount.entrySet().stream()
                .sorted(Map.Entry.<Livro, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Livro> mostrarTopSubgeneroRequisicoes(String subgenero) {
        Map<Livro, Long> livroCount = requisicoes.stream()
                .filter(requisicao -> requisicao.getExemplar().getLivro().getSubGenero().equalsIgnoreCase(subgenero))
                .collect(Collectors.groupingBy(requisicao -> requisicao.getExemplar().getLivro(), Collectors.counting()));

        return livroCount.entrySet().stream()
                .sorted(Map.Entry.<Livro, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void guardar() {
        try (FileOutputStream fileOut = new FileOutputStream("gestor_requisicoes.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Serialized data is saved: gestor_requisicoes.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
