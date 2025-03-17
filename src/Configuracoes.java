import Gestores.GestorConfiguracoes;
import Gestores.GestorLivros;
import Gestores.GestorSocios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configuracoes extends JFrame
{
    private JTextField diasReqAluno;
    private JTextField diasReqProf;
    private JTextField diasReqFunc;
    private JButton btnGuardar;
    private JButton btnVoltar;
    private JTextField multaAluno;
    private JTextField multaProf;
    private JTextField multaFunc;
    private JTextField maxLivrosAluno;
    private JTextField maxLivrosProf;
    private JTextField maxLivrosFunc;
    private JTextField anuidadeAluno;
    private JTextField anuidadeProf;
    private JTextField anuidadeFunc;
    private JPanel panel;

    public Configuracoes()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        apresenta();

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardar();
            }
        });
    }

    private void btnGuardar() {
        try {
            if (!diasReqAluno.getText().isEmpty() && !diasReqProf.getText().isEmpty() &&
                    !diasReqFunc.getText().isEmpty() && !multaAluno.getText().isEmpty() &&
                    !multaProf.getText().isEmpty() && !multaFunc.getText().isEmpty() && !maxLivrosFunc.getText().isEmpty() &&
                    !maxLivrosAluno.getText().isEmpty() && !maxLivrosProf.getText().isEmpty() && !anuidadeAluno.getText().isEmpty() &&
                    !anuidadeProf.getText().isEmpty() && !anuidadeFunc.getText().isEmpty())
            {
                GestorConfiguracoes.getInstance().atualizar(Short.parseShort(diasReqAluno.getText()), Short.parseShort(diasReqProf.getText()),
                        Short.parseShort(diasReqFunc.getText()), Short.parseShort(multaAluno.getText()),
                                Short.parseShort(multaProf.getText()), Short.parseShort(multaFunc.getText()), Short.parseShort(maxLivrosAluno.getText()),
                        Short.parseShort(maxLivrosProf.getText()), Short.parseShort(maxLivrosFunc.getText()), Short.parseShort(anuidadeAluno.getText()),
                        Short.parseShort(anuidadeProf.getText()), Short.parseShort(anuidadeFunc.getText()));
                mostraPopUp();
                new EcraPrincipal();
                dispose();
            }
            else
            {
                apresentaMensagem();
            }

        }
        catch (Exception ex) {
            apresentaMensagem();
        }
    }

    private static void apresentaMensagem() {
        JOptionPane.showMessageDialog(null, "Os dados inseridos nao estao validos.");
    }

    private void btnVoltar() {
        new EcraPrincipal();
        dispose();
    }

    private void mostraPopUp() {
        JOptionPane.showMessageDialog(null, "As configurações foram guardadas.");
    }

    private void apresenta()
    {
        GestorConfiguracoes gestorConfiguracoes = GestorConfiguracoes.getInstance();

        diasReqAluno.setText(String.valueOf(gestorConfiguracoes.getNumMaxDiasRequisicaoAluno()));
        diasReqProf.setText(String.valueOf(gestorConfiguracoes.getNumMaxDiasRequisicaoProfessor()));
        diasReqFunc.setText(String.valueOf(gestorConfiguracoes.getNumMaxDiasRequisicaoFuncionario()));

        multaAluno.setText(String.valueOf(gestorConfiguracoes.getValorMultaAluno()));
        multaProf.setText(String.valueOf(gestorConfiguracoes.getValorMultaProfessor()));
        multaFunc.setText(String.valueOf(gestorConfiguracoes.getValorMultaFuncionario()));

        maxLivrosAluno.setText(String.valueOf(gestorConfiguracoes.getNumMaxLivrosRequisitadosAluno()));
        maxLivrosProf.setText(String.valueOf(gestorConfiguracoes.getNumMaxLivrosRequisitadosProfessor()));
        maxLivrosFunc.setText(String.valueOf(gestorConfiguracoes.getNumMaxLivrosRequisitadosFuncionario()));

        anuidadeAluno.setText(String.valueOf(gestorConfiguracoes.getValorAnuidadeAluno()));
        anuidadeProf.setText(String.valueOf(gestorConfiguracoes.getValorAnuidadeProfessor()));
        anuidadeFunc.setText(String.valueOf(gestorConfiguracoes.getValorAnuidadeFuncionario()));
    }
}
