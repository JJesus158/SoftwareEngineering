import Enum.EstadoRequisicao;
import Gestores.GestorRequisicoes;
import Modelos.Requisicao;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class HistoricoPagamento extends JFrame {
    private JTextField textoPesquisa;
    private JButton btnVoltar;
    private JComboBox<String> btnFiltro;
    private JScrollPane scrollPane;
    private JPanel listaRequisicoes;
    private JPanel panel;
    private JButton btnNotificar;

    public HistoricoPagamento(Socio socio) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);



        // Set up the action listener for the "Voltar" button
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EcraPrincipal();
                dispose();
            }
        });

        atualizarListaRequisicoes(GestorRequisicoes.getInstance().mostrarRequisicoesPorCodigoDeSocio(String.valueOf(socio.getId())));

        if(socio.getEstadoPagamento().equals(EstadoRequisicao.EMDIVIDA)) {
            JButton btnNotifica = new JButton("Notificar");
            btnNotifica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(panel, "Notificação enviada para o sócio em dívida.");
                }
            });
        }

    }

    private void atualizarListaRequisicoes(LinkedList<Requisicao> requisicoes) {
        listaRequisicoes.removeAll();
        listaRequisicoes.setLayout(new BoxLayout(listaRequisicoes, BoxLayout.Y_AXIS));

        for (Requisicao requisicao : requisicoes) {
            JPanel requisicaoPanel = new JPanel();
            requisicaoPanel.setLayout(new GridLayout(1, 4)); // Adjust based on your Requisicao object
            requisicaoPanel.add(new JLabel( requisicao.getId() + ""));
            requisicaoPanel.add(new JLabel("" + requisicao.getDataInicio()));
            requisicaoPanel.add(new JLabel("" + requisicao.getDataFim()));
            requisicaoPanel.add(new JLabel("" + requisicao.getSocio().getEstadoPagamento()));
            requisicaoPanel.add(new JLabel("" + requisicao.calcularMulta()));

            listaRequisicoes.add(requisicaoPanel);
        }

        listaRequisicoes.revalidate();
        listaRequisicoes.repaint();
    }

}
