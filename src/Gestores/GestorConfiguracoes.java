package Gestores;

import java.io.*;

public class GestorConfiguracoes implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    private static GestorConfiguracoes instance= null;

    private short numMaxDiasRequisicaoAluno;
    private short numMaxDiasRequisicaoProfessor;
    private short numMaxDiasRequisicaoFuncionario;

    private short valorMultaAluno;
    private short valorMultaProfessor;
    private short valorMultaFuncionario;

    private short numMaxLivrosRequisitadosAluno;
    private short numMaxLivrosRequisitadosProfessor;
    private short numMaxLivrosRequisitadosFuncionario;

    private short valorAnuidadeAluno;
    private short valorAnuidadeProfessor;
    private short valorAnuidadeFuncionario;

    public GestorConfiguracoes()
    {
        numMaxDiasRequisicaoAluno = 0;
        numMaxDiasRequisicaoProfessor = 0;
        numMaxDiasRequisicaoFuncionario = 0;

        valorMultaAluno = 0;
        valorMultaProfessor = 0;
        valorMultaFuncionario = 0;

        numMaxLivrosRequisitadosAluno = 0;
        numMaxLivrosRequisitadosProfessor = 0;
        numMaxLivrosRequisitadosFuncionario = 0;

        valorAnuidadeAluno = 0;
        valorAnuidadeProfessor = 0;
        valorAnuidadeFuncionario = 0;
    }


    public short getNumMaxDiasRequisicaoAluno() {
        return numMaxDiasRequisicaoAluno;
    }

    public short getNumMaxDiasRequisicaoProfessor() {
        return numMaxDiasRequisicaoProfessor;
    }

    public short getNumMaxDiasRequisicaoFuncionario() {
        return numMaxDiasRequisicaoFuncionario;
    }

    public static void setInstance(GestorConfiguracoes instance) {
        GestorConfiguracoes.instance = instance;
    }

    public short getValorMultaAluno() {
        return valorMultaAluno;
    }

    public short getValorMultaProfessor() {
        return valorMultaProfessor;
    }

    public short getValorMultaFuncionario() {
        return valorMultaFuncionario;
    }

    public short getNumMaxLivrosRequisitadosAluno() {
        return numMaxLivrosRequisitadosAluno;
    }

    public short getNumMaxLivrosRequisitadosProfessor() {
        return numMaxLivrosRequisitadosProfessor;
    }

    public short getNumMaxLivrosRequisitadosFuncionario() {
        return numMaxLivrosRequisitadosFuncionario;
    }

    public short getValorAnuidadeAluno() {
        return valorAnuidadeAluno;
    }

    public short getValorAnuidadeProfessor() {
        return valorAnuidadeProfessor;
    }

    public short getValorAnuidadeFuncionario() {
        return valorAnuidadeFuncionario;
    }

    public static GestorConfiguracoes getInstance() {
        if(instance == null){
            instance = new GestorConfiguracoes();
        }
        return instance;
    }



    public void atualizar(short numMaxDiasRequisicaoAluno, short numMaxDiasRequisicaoProfessor, short numMaxDiasRequisicaoFuncionario, short valorMultaAluno, short valorMultaProfessor, short valorMultaFuncionario, short numMaxLivrosRequisitadosAluno, short numMaxLivrosRequisitadosProfessor, short numMaxLivrosRequisitadosFuncionario, short valorAnuidadeAluno, short valorAnuidadeProfessor, short valorAnuidadeFuncionario) {
        this.numMaxDiasRequisicaoAluno = numMaxDiasRequisicaoAluno;
        this.numMaxDiasRequisicaoProfessor = numMaxDiasRequisicaoProfessor;
        this.numMaxDiasRequisicaoFuncionario = numMaxDiasRequisicaoFuncionario;
        this.valorMultaAluno = valorMultaAluno;
        this.valorMultaProfessor = valorMultaProfessor;
        this.valorMultaFuncionario = valorMultaFuncionario;
        this.numMaxLivrosRequisitadosAluno = numMaxLivrosRequisitadosAluno;
        this.numMaxLivrosRequisitadosProfessor = numMaxLivrosRequisitadosProfessor;
        this.numMaxLivrosRequisitadosFuncionario = numMaxLivrosRequisitadosFuncionario;
        this.valorAnuidadeAluno = valorAnuidadeAluno;
        this.valorAnuidadeProfessor = valorAnuidadeProfessor;
        this.valorAnuidadeFuncionario = valorAnuidadeFuncionario;
        guardar();
    }

    public void guardar() {
        try (FileOutputStream fileOut = new FileOutputStream("configuracoes.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Serialized data is saved: configuracoes.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
