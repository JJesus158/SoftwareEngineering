import Gestores.GestorConfiguracoes;
import Gestores.GestorLivros;
import Gestores.GestorRequisicoes;
import Gestores.GestorSocios;
import Modelos.Exemplar;
import Modelos.Requisicao;
import Modelos.Socio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdicionarRequisicao extends JFrame {
    private JTextField numSocio;
    private JButton btnVoltar;
    private JButton btnConfirmar;
    private JTextField diaEntrega;
    private JTextField mesEntrega;
    private JTextField anoEntrega;
    private JPanel panel;
    private JLabel dataAtual;
    private JLabel labelexemplar;

    public AdicionarRequisicao(Exemplar exemplar) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);


        LocalDate dataAtualLocalDate = LocalDate.now();
        String valorDataAtual = dataAtualLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


        labelexemplar.setText("Titulo: " + exemplar.getLivro().getTitulo() + "    " + "Autor(es):" + exemplar.getLivro().getAutores() + "     " + " Exemplar: " + exemplar.getCodigo());
        dataAtual.setText(valorDataAtual);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Exemplares(exemplar.getLivro());
                dispose();
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dataEntrega = verificarDataEntrega(dataAtualLocalDate);
                Socio socio = verifcarSocio();

                if (socio == null && dataEntrega == null) {
                    return;
                }

                if (exemplar.isRequisitado()){
                    JOptionPane.showMessageDialog(null, "Exemplar já requisitado");
                    return;
                }

                switch (socio.getTipo()){
                    case ALUNO:
                        if (socio.getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()){
                            JOptionPane.showMessageDialog(null, "Não pode ter mais de "+GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()+" livros requisitados", "Erro", JOptionPane.ERROR_MESSAGE);
                            return ;
                        }
                        break;
                    case PROFESSOR:
                        if (socio.getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosProfessor()){
                            JOptionPane.showMessageDialog(null, "Não pode ter mais de "+GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()+" livros requisitados", "Erro", JOptionPane.ERROR_MESSAGE);
                            return ;
                        }
                        break;
                    case FUNCIONARIO:
                        if (socio.getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosFuncionario()){
                            JOptionPane.showMessageDialog(null, "Não pode ter mais de "+GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()+" livros requisitados", "Erro", JOptionPane.ERROR_MESSAGE);
                            return ;
                        }
                        break;
                }

                Requisicao requisicao = new Requisicao(dataEntrega, exemplar, socio);
                exemplar.requisitar(requisicao);
                socio.adicionarRequisicao(requisicao);
                GestorRequisicoes.getInstance().inserir(requisicao);
                GestorRequisicoes.getInstance().guardar();
                GestorSocios.getInstance().guardar();
                GestorLivros.getInstance().guardar();
                new HistoricoDeRequisicoes();
                dispose();

            }
        });
    }

    public Socio verifcarSocio() {
        Socio socio = null;
        String numSocioText = numSocio.getText().trim();

        if (numSocioText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o ID do sócio", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }


        try {
            int id = Integer.parseInt(numSocioText);
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "ID do sócio inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                socio = GestorSocios.getInstance().pesquisarPorId(id);
                if (socio == null) {
                    JOptionPane.showMessageDialog(this, "Não existe nenhum sócio com este ID", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                switch (socio.getTipo()){
                    case ALUNO:
                        if (socio.getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()){
                            JOptionPane.showMessageDialog(null, "Não pode ter mais de "+GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosAluno()+" livros requisitados", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case PROFESSOR:
                        if (socio.getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosProfessor()){
                            JOptionPane.showMessageDialog(null, "Não pode ter mais de "+GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosProfessor()+" livros requisitados", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case FUNCIONARIO:
                        if (socio.getnRequisicoesAtuais()> GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosFuncionario()){
                            JOptionPane.showMessageDialog(null, "Não pode ter mais de "+GestorConfiguracoes.getInstance().getNumMaxLivrosRequisitadosFuncionario()+" livros requisitados", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID do sócio deve ser um número inteiro", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return socio;
    }

    public LocalDate verificarDataEntrega(LocalDate dataAtualLocalDate) {
        if (diaEntrega.getText().isEmpty() || mesEntrega.getText().isEmpty() || anoEntrega.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        int dia, mes, ano;

        try {
            dia = Integer.parseInt(diaEntrega.getText());
            mes = Integer.parseInt(mesEntrega.getText());
            ano = Integer.parseInt(anoEntrega.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1900 || ano > 2099) {
            JOptionPane.showMessageDialog(this, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Formate a data de entrega
        String dataEntregaStr = String.format("%04d-%02d-%02d", ano, mes, dia);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate dataEntrega = LocalDate.parse(dataEntregaStr, formatter);

            if (dataEntrega.isBefore(dataAtualLocalDate) || dataEntrega.equals(dataAtualLocalDate)) {
                JOptionPane.showMessageDialog(this, "Data de entrega deve ser posterior a data atual", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return dataEntrega;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}