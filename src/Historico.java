import Gestores.GestorRequisicoes;
import Modelos.Requisicao;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Historico extends JFrame {

    private JPanel panel;
    private JLabel titulo;
    private JPanel listaLivros;
    private JButton btnVoltar;
    private JScrollPane scrollPane;
    public Historico(Socio socio) {

        LinkedList<Requisicao> requisicoes;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);
        listaLivros.setPreferredSize(new Dimension(600, 999999));

        titulo.setText("Histórico do Sócio" + socio.getId());

        // mostra todas as requisicoes para o sócio
        requisicoes = GestorRequisicoes.getInstance().mostrarRequisicoesPorCodigoDeSocio(String.valueOf(socio.getId()));
        apresenta(requisicoes);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Socios();
                dispose();
            }
        });
    }

    private void apresenta(LinkedList<Requisicao> requisicoes) {
        listaLivros.removeAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Requisicao requisicao : requisicoes) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel tituloLivro = new JLabel(requisicao.getExemplar().getLivro().getTitulo() + " - " + requisicao.getExemplar().getLivro().getAutores());
            panel.add(tituloLivro, BorderLayout.NORTH);

            JLabel dataRequisicao = new JLabel("Data de requisição: " + requisicao.getDataInicio().format(formatter));
            JLabel dataDevolucao = new JLabel("Data de devolução: " + (requisicao.getDataFim() != null ? requisicao.getDataFim().format(formatter) : "--/--/----"));

            JPanel panelDatas = new JPanel(new GridLayout(2, 1));
            panelDatas.add(dataRequisicao);
            panelDatas.add(dataDevolucao);

            panel.add(panelDatas, BorderLayout.CENTER);

            listaLivros.add(panel);
        }

        listaLivros.revalidate();
        listaLivros.repaint();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}