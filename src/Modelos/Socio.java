package Modelos;

import Enum.ContactoPredefinido;
import Enum.EstadoRequisicao;
import Enum.TipoSocio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Socio implements Serializable {
    private static int proxId = 1000;
    private int id;
    private String nome;
    private int idade;
    private int CC;
    private int nif;
    private String email;
    private int telefone;
    private String morada;
    private TipoSocio tipo;
    private ContactoPredefinido contactoPredefinido;
    private short nRequisicoesAtuais;
    private LinkedList<Requisicao> requisicoes;
    private Date expiracaoAnuidade;

    public Socio( String nome, int idade, int CC, int nif, String email, int telefone, String morada, TipoSocio tipo, ContactoPredefinido contactoPredefinido) {

        this.id = proxId++;
        this.nome = nome;
        this.idade = idade;
        this.CC = CC;
        this.nif = nif;
        this.email = email;
        this.telefone = telefone;
        this.morada = morada;
        this.tipo = tipo;
        this.contactoPredefinido = contactoPredefinido.EMAIL;
        nRequisicoesAtuais = 0;
        //requisicoesAtuais = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        this.expiracaoAnuidade = calendar.getTime(); // data de expiracao da anuidade é sysdate + 1 ano
        this.requisicoes = new LinkedList<>();
    }
    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getCC() {
        return CC;
    }

    public int getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getMorada() {
        return morada;
    }

    public TipoSocio getTipo() {
        return tipo;
    }

    public ContactoPredefinido getContactoPredefinido() {
        return contactoPredefinido;
    }

    public short getnRequisicoesAtuais() {
        return nRequisicoesAtuais;
    }

    public Date getExpiracaoAnuidade() {
        return expiracaoAnuidade;
    }


    public void editarSocio(String nome, int idade, int CC, int nif, String email, int telefone, ContactoPredefinido contactoPredefinido, String morada, TipoSocio tipo) {
        this.nome = nome;
        this.idade = idade;
        this.CC = CC;
        this.nif = nif;
        this.email = email;
        this.telefone = telefone;
        this.contactoPredefinido = contactoPredefinido;
        this.morada = morada;
        this.tipo = tipo;
    }
    public boolean isAluno() {
        return this.tipo == TipoSocio.ALUNO;
    }

    public boolean isProfessor() {
        return this.tipo == TipoSocio.PROFESSOR;
    }

    public boolean isFuncionario() {
        return this.tipo == TipoSocio.FUNCIONARIO;
    }

    public void adicionarRequisicao(Requisicao requisicao)
    {
        requisicoes.add(requisicao);
        nRequisicoesAtuais++;
    }

    public void devolverRequisicao(){
        nRequisicoesAtuais--;
    }

    public void pagarAnuidade(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        this.expiracaoAnuidade = calendar.getTime(); // data de expiracao da anuidade é sysdate + 1 ano
    }

    public EstadoRequisicao getEstadoPagamento(){
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getEstado() == EstadoRequisicao.EMDIVIDA) {
                return EstadoRequisicao.EMDIVIDA;
            }
        }
        return EstadoRequisicao.PAGO;
    }

}

