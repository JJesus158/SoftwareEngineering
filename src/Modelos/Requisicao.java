package Modelos;

import Enum.EstadoRequisicao;
import Gestores.GestorConfiguracoes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Requisicao implements Serializable {
    private static int nextId = 1000;
    private int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private EstadoRequisicao estado;
    private Exemplar exemplar;
    private Socio socio;

    public Requisicao(LocalDate dataFim, Exemplar exemplar, Socio socio) {
        this.id = nextId++;
        this.dataInicio= LocalDate.now();
        this.dataFim = dataFim;
        this.estado = EstadoRequisicao.EMANDAMENTO;
        this.exemplar = exemplar;
        this.socio = socio;

    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public EstadoRequisicao getEstado() {
        LocalDate dataAtual = LocalDate.now();

        if (dataAtual.isAfter(dataFim) && !estado.equals(EstadoRequisicao.PAGO)) {
            estado = EstadoRequisicao.EMDIVIDA;
        }

        return estado;
    }

    public double calcularMulta() {

        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isAfter(dataFim)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataFim, dataAtual);
            double valorMulta = GestorConfiguracoes.getInstance().getValorMultaAluno();
            return diasAtraso * valorMulta;
        }
        return 0.0;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Socio getSocio() {
        return socio;
    }

    public void devolverRequisicao(){
        estado = EstadoRequisicao.PAGO;
    }

    public int getId() {
        return id;
    }
}
