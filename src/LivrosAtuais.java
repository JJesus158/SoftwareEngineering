import Gestores.GestorRequisicoes;
import Modelos.Requisicao;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class LivrosAtuais extends JFrame {

    private JPanel panel;
    private JLabel titulo;
    private JPanel listaLivros;
    private JButton btnVoltar;
    private JScrollPane scrollPane;


    public LivrosAtuais(Socio socio)
    {
        LinkedList<Requisicao> requisicoes;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);
        listaLivros.setPreferredSize(new Dimension(600, 999999));

        titulo.setText("Livros do Sócio" + socio.getId());

        // mostra as requisicoes em adamento para o sócio
        requisicoes = GestorRequisicoes.getInstance().mostrarRequisicoesPorCodigoDeSocioEmAndamento(String.valueOf(socio.getId()));
        apresenta(requisicoes);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerfilSocio(socio);
                dispose();
            }
        });
    }

    private void apresenta(LinkedList<Requisicao> requisicoes) {
        listaLivros.removeAll();

        for (Requisicao requisicao : requisicoes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel tituloLivro = new JLabel(requisicao.getExemplar().getLivro().getTitulo() + " - " + requisicao.getExemplar().getLivro().getAutores());
            panel.add(tituloLivro, BorderLayout.CENTER);

            JButton btnDevolver = new JButton("Efetuar Devolução");
            btnDevolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ConfirmacaoDevolucao(requisicao.getExemplar());
                    dispose();
                }
            });
            panel.add(btnDevolver, BorderLayout.EAST);

            listaLivros.add(panel);
        }

        listaLivros.revalidate();
        listaLivros.repaint();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
