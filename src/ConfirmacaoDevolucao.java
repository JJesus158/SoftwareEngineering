import Gestores.GestorLivros;
import Gestores.GestorRequisicoes;
import Gestores.GestorSocios;
import Modelos.Exemplar;
import Modelos.Requisicao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConfirmacaoDevolucao extends JFrame {
    private JButton btnVoltar;
    private JButton btnConfirmar;
    private JPanel panel;
    private JLabel labelCodigoExemplar;
    private JLabel labelDataAtual;
    private JLabel labelMulta;
    private JLabel labelCodSocio;
    private JLabel labelDataEntrega;
    private JLabel labelDataRequisicao;


    public ConfirmacaoDevolucao(Exemplar exemplar) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Requisicao requisicao = GestorRequisicoes.getInstance().pesquisarrRequisicaoPorExemplarRequisitado(exemplar);

        labelCodigoExemplar.setText(String.valueOf(exemplar.getCodigo()));


        labelCodSocio.setText("# " + requisicao.getSocio().getId());
        labelDataRequisicao.setText(requisicao.getDataInicio().format(formatter));
        labelDataEntrega.setText(requisicao.getDataFim().format(formatter));
        labelDataAtual.setText(dataAtual.format(formatter));
        labelMulta.setText(requisicao.calcularMulta() +" $");
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
                requisicao.getSocio().devolverRequisicao();
                exemplar.devolver();
                requisicao.devolverRequisicao();
                GestorRequisicoes.getInstance().guardar();
                GestorSocios.getInstance().guardar();
                GestorLivros.getInstance().guardar();

                dispose();
            }
        });
    }


}