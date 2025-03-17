import Gestores.GestorLivros;
import Modelos.Exemplar;
import Modelos.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Exemplares extends JFrame {
    private JLabel tituloAutor;
    private JPanel panel;
    private JButton btnAdicionar;
    private JButton btnVoltar;
    private JScrollPane scrollPanel;
    private JPanel listExemplares;
    public Exemplares(Livro livro) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);
        listExemplares.setPreferredSize(new Dimension(450, 999999));

        tituloAutor.setText("Exemplares de: " + livro.getTitulo() + ", " + livro.getAutores());

        LinkedList<Exemplar> exemplares = livro.getExemplares();

        for (Exemplar exemplar: exemplares)
        {
            JLabel label = new JLabel(exemplar.getCodigo() + " - " + (exemplar.isRequisitado() ? "Requisitado" : "Disponivel"));
            label.setHorizontalAlignment(SwingConstants.LEFT);
            listExemplares.add(label);
            if (!exemplar.isRequisitado())
            {
                criarBtnRequisitar(listExemplares, exemplar);
            }
            else
            {
                criarBtnDevolver(listExemplares, exemplar);
            }
            JButton btnRemover = new JButton("Remover");
            btnRemover.setPreferredSize(new Dimension(200, 50));
            btnRemover.setMaximumSize(new Dimension(200, 50));
            btnRemover.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnRemover.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (exemplar.isRequisitado())
                    {
                        apresentaMensagem();
                    }
                    else
                    {
                        GestorLivros.getInstance().removerExemplar(exemplar, livro);
                        dispose();
                        new Exemplares(livro);
                    }

                }
            });
            listExemplares.add(btnRemover);

        }

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar(livro);
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAdicionar(livro);
            }
        });
    }

    private void btnAdicionar(Livro livro) {
        GestorLivros.getInstance().criarExemplar(livro);
        dispose();
        new Exemplares(livro);
    }

    private void btnVoltar(Livro livro) {
        new LivroDetalhes(livro);
        dispose();
    }

    private static void apresentaMensagem() {
        JOptionPane.showMessageDialog(null, "O exemplar não pode ser eliminado porque encontra-se requisitado");
    }

    private void criarBtnDevolver(JPanel lista, Exemplar exemplar)
    {
        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setPreferredSize(new Dimension(200, 50));
        btnDevolver.setMaximumSize(new Dimension(200, 50));
        btnDevolver.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfirmacaoDevolucao(exemplar);
            }
        });
        lista.add(btnDevolver);
    }

    private void criarBtnRequisitar(JPanel lista, Exemplar exemplar) {
        JButton btnRequisitar = new JButton("Requisitar");
        btnRequisitar.setPreferredSize(new Dimension(200, 50));
        btnRequisitar.setMaximumSize(new Dimension(200, 50));
        btnRequisitar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRequisitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdicionarRequisicao(exemplar);
                dispose();
            }
        });
        lista.add(btnRequisitar);
    }
}
