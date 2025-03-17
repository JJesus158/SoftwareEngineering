import Gestores.GestorLivros;
import Modelos.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Aquisicoes extends JFrame
{
    private JTextField textoPesquisa;
    private JComboBox<String> btnFiltro;
    private JPanel listaLivros;
    private JPanel panel;
    private JButton btnAdicionar;
    private JButton btnVoltar;
    private JScrollPane scrollPane;


    public Aquisicoes()
    {
        LinkedList<Livro> livros;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);
        listaLivros.setPreferredSize(new Dimension(600, 999999));

        btnFiltro.addItem("Todos");
        btnFiltro.addItem("Disponiveis");

        // mostra todos assim que abre o ecra
        livros = GestorLivros.getInstance().mostrarTodosLivros();
        apresenta(livros);

        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFiltro();

            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar();
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAdicionar();
            }
        });
    }

    private void btnFiltro() {
        LinkedList<Livro> livros;

        if (btnFiltro.getSelectedIndex() == 0)
        {
            livros = textoPesquisa.getText().compareTo("") == 0 ? GestorLivros.getInstance().mostrarTodosLivros() : GestorLivros.getInstance().pesquisarPorTituloAutor(textoPesquisa.getText()) ;
        }
        else
        {
            livros = textoPesquisa.getText().compareTo("") == 0 ? GestorLivros.getInstance().mostrarDisponiveis() : GestorLivros.getInstance().pesquisarDisponiveisPorTituloAutor(textoPesquisa.getText()) ;
        }
        apresenta(livros);
    }

    private void btnVoltar() {
        new EcraPrincipal();
        dispose();
    }

    private void btnAdicionar() {
        new AdicionarLivro();
        dispose();
    }

    private void apresenta(LinkedList<Livro> livros)
    {
        listaLivros.removeAll();
        for (Livro livro : livros) {
            JButton btnLivro = new JButton(livro.toString());
            btnLivro.setPreferredSize(new Dimension(500, 100));
            btnLivro.setMaximumSize(new Dimension(500, 100));
            btnLivro.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnLivro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnLivro(livro);
                }
            });
            listaLivros.add(btnLivro);
        }
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void btnLivro(Livro livro) {
        new LivroDetalhes(livro);
        dispose();
    }
}
