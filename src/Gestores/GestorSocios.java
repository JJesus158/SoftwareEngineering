package Gestores;

import Enum.EstadoRequisicao;
import Modelos.Socio;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class GestorSocios implements Serializable
{
    private final LinkedList<Socio> socios;
    @Serial
    private static final long serialVersionUID = 1L;
    private static GestorSocios instance = null;

    public GestorSocios()
    {
        socios = new LinkedList<>();
    }

    public static GestorSocios getInstance() {
        if(instance == null){
            instance = new GestorSocios();
        }
        return instance;
    }

    public LinkedList<Socio> mostrarTodosSocios()
    {
        return new LinkedList<>(socios);
    }

    public LinkedList<Socio> pesquisarPorNome(String nome)
    {
        LinkedList<Socio> pesquisa = new LinkedList<>();

        for (Socio socio : socios)
        {
            if (socio.getNome().contains(nome))
            {
                pesquisa.add(socio);
            }
        }
        return pesquisa;
    }

    public Socio pesquisarPorNif(int nif)
    {

        for (Socio socio : socios)
        {
            if (socio.getNif() == nif)
            {
                return socio;
            }
        }
        return null;
    }

    public Socio pesquisarPorId(int id)
    {

        for (Socio socio : socios)
        {
            if (socio.getId() == id)
            {
                return socio;
            }
        }
        return null;
    }

    public void inserir(Socio socio)
    {
        for (Socio atual : socios)
        {
            if (socio.getNif() == atual.getNif())
            {
                JOptionPane.showMessageDialog(null, "Não podem haver sócios com nif repetido");
                return;
            }

            if (socio.getCC() == atual.getCC())
            {
                JOptionPane.showMessageDialog(null, "Não podem haver sócios com CC repetido");
                return;
            }
        }
        socios.add(socio);
        guardar();
    }

    public void remover(Socio socio)
    {
        socios.remove(socio);
        guardar();
    }


    public void guardar() {
        try (FileOutputStream fileOut = new FileOutputStream("gestor_socios.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Serialized data is saved: gestor_socios.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void setInstance(GestorSocios instance) {
        GestorSocios.instance = instance;
    }

    public boolean contem(int id) {
        return pesquisarPorId(id) != null;
    }

    public LinkedList<Socio> mostrarAlunos()
    {
        LinkedList<Socio> alunos = new LinkedList<>();

        for (Socio socio : socios)
        {
            if (socio.isAluno())
            {
                alunos.add(socio);
            }
        }

        return alunos;
    }

    public LinkedList<Socio> mostrarProfessores()
    {
        LinkedList<Socio> professores = new LinkedList<>();

        for (Socio socio : socios)
        {
            if (socio.isProfessor())
            {
                professores.add(socio);
            }
        }

        return professores;
    }

    public LinkedList<Socio> mostrarFuncionarios()
    {
        LinkedList<Socio> funcionarios = new LinkedList<>();

        for (Socio socio : socios)
        {
            if (socio.isFuncionario())
            {
                funcionarios.add(socio);
            }
        }

        return funcionarios;
    }

    public LinkedList<Socio> mostrarSociosPago() {
        LinkedList<Socio> socios = new LinkedList<>();
        for (Socio socio : this.socios) {
            if (socio.getEstadoPagamento() == EstadoRequisicao.PAGO) {
                socios.add(socio);
            }
        }
        return socios;
    }

    public LinkedList<Socio> mostrarSociosEmDivida() {
        LinkedList<Socio> socios = new LinkedList<>();
        for (Socio socio : this.socios) {
            if (socio.getEstadoPagamento() == EstadoRequisicao.EMDIVIDA) {
                socios.add(socio);
            }
        }
        return socios;
    }

    public LinkedList<Socio> pesquisarPorNomePago(String searchTerm) {
        LinkedList<Socio> socios = new LinkedList<>();
        for (Socio socio : this.socios) {
            if (socio.getEstadoPagamento() == EstadoRequisicao.PAGO) {
                if (socio.getNome().contains(searchTerm)) {
                    socios.add(socio);
                }
            }
        }
        return socios;
    }

    public LinkedList<Socio> pesquisarPorNomeEmDivida(String searchTerm) {
        LinkedList<Socio> socios = new LinkedList<>();
        for (Socio socio : this.socios) {
            if (socio.getEstadoPagamento() == EstadoRequisicao.EMDIVIDA) {
                if (socio.getNome().contains(searchTerm)) {
                    socios.add(socio);
                }
            }
        }
        return socios;
    }
}
