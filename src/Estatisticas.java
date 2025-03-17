import Gestores.GestorRequisicoes;
import Modelos.Livro;
import Modelos.Genero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Estatisticas extends JFrame {
    private JComboBox<String> btnFiltro;
    private JPanel listaTopLivros;
    private JPanel panel;
    private JButton btnVoltar;
    private JScrollPane scrollPane;
    private JLabel titulo;

    public Estatisticas() {
        List<Livro> livros;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);
        listaTopLivros.setPreferredSize(new Dimension(600, 999999));

        btnFiltro.addItem("Geral");
        btnFiltro.addItem("Autor");
        btnFiltro.addItem("Genero");
        btnFiltro.addItem("Subgenero");

        // mostra top geral quando abre o ecra
        livros = GestorRequisicoes.getInstance().mostrarTopGeralRequisicoes();
        apresentaTopLivros(livros);

        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (btnFiltro.getSelectedIndex()) {
                    case 0:
                        apresentaTopLivros(GestorRequisicoes.getInstance().mostrarTopGeralRequisicoes());
                        break;

                    case 1:
                        mostraListaAutores();
                        break;

                    case 2:
                        mostraListaGeneros();
                        break;

                    case 3:
                        mostraListaSubgeneros();
                        break;
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EcraPrincipal();
                dispose();
            }
        });
    }

    private void apresentaTopLivros(List<Livro> livros) {
        listaTopLivros.removeAll();
        for (Livro livro : livros) {
            JPanel livroPanel = new JPanel();
            livroPanel.setLayout(new BoxLayout(livroPanel, BoxLayout.Y_AXIS));
            livroPanel.setPreferredSize(new Dimension(500, 100));
            livroPanel.setMaximumSize(new Dimension(500, 100));
            livroPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel tituloLabel = new JLabel(livro.getTitulo());
            JLabel autorLabel = new JLabel(livro.getAutores());

            livroPanel.add(tituloLabel);
            livroPanel.add(autorLabel);

            listaTopLivros.add(livroPanel);
        }
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listaTopLivros.revalidate();
        listaTopLivros.repaint();
    }

    private void mostraListaAutores() {
        JDialog dialog = new JDialog(this, "Selecione um Autor", true);
        dialog.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> autores = (List<String>) GestorRequisicoes.getInstance().obterTodosAutores();

        for (String autor : autores) {
            listModel.addElement(autor);
        }

        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(list);
        dialog.add(listScroller, BorderLayout.CENTER);

        JButton selecionarBtn = new JButton("Selecionar");
        selecionarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autorSelecionado = list.getSelectedValue();
                if (autorSelecionado != null) {
                    List<Livro> livros = GestorRequisicoes.getInstance().mostrarTopAutorRequisicoes(autorSelecionado);
                    apresentaTopLivros(livros);
                }
                dialog.dispose();
            }
        });

        dialog.add(selecionarBtn, BorderLayout.SOUTH);
        dialog.setSize(300, 400);
        dialog.setVisible(true);
    }

    private void mostraListaGeneros() {
        JDialog dialog = new JDialog(this, "Selecione um Género", true);
        dialog.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> generos = Genero.INSTANCIA.getGeneros();

        for (String genero : generos) {
            listModel.addElement(genero);
        }

        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(list);
        dialog.add(listScroller, BorderLayout.CENTER);

        JButton selecionarBtn = new JButton("Selecionar");
        selecionarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generoSelecionado = list.getSelectedValue();
                if (generoSelecionado != null) {
                    List<Livro> livros = GestorRequisicoes.getInstance().mostrarTopGeneroRequisicoes(generoSelecionado);
                    apresentaTopLivros(livros);
                }
                dialog.dispose();
            }
        });

        dialog.add(selecionarBtn, BorderLayout.SOUTH);
        dialog.setSize(300, 400);
        dialog.setVisible(true);
    }

    private void mostraListaSubgeneros() {
        JDialog dialog = new JDialog(this, "Selecione um Género e Subgénero", true);
        dialog.setLayout(new BorderLayout());

        DefaultListModel<String> generoListModel = new DefaultListModel<>();
        List<String> generos = Genero.INSTANCIA.getGeneros();

        for (String genero : generos) {
            generoListModel.addElement(genero);
        }

        JList<String> generoList = new JList<>(generoListModel);
        generoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane generoListScroller = new JScrollPane(generoList);
        dialog.add(generoListScroller, BorderLayout.CENTER);

        JButton selecionarGeneroBtn = new JButton("Selecionar Género");
        selecionarGeneroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generoSelecionado = generoList.getSelectedValue();
                if (generoSelecionado != null) {
                    dialog.remove(generoListScroller);
                    dialog.remove(selecionarGeneroBtn);

                    DefaultListModel<String> subgeneroListModel = new DefaultListModel<>();
                    List<String> subgeneros = Genero.INSTANCIA.getSubgenerosPorGenero(generoSelecionado);

                    for (String subgenero : subgeneros) {
                        subgeneroListModel.addElement(subgenero);
                    }

                    JList<String> subgeneroList = new JList<>(subgeneroListModel);
                    subgeneroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    JScrollPane subgeneroListScroller = new JScrollPane(subgeneroList);
                    dialog.add(subgeneroListScroller, BorderLayout.CENTER);

                    JButton selecionarSubgeneroBtn = new JButton("Selecionar Subgénero");
                    selecionarSubgeneroBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String subgeneroSelecionado = subgeneroList.getSelectedValue();
                            if (subgeneroSelecionado != null) {
                                List<Livro> livros = GestorRequisicoes.getInstance().mostrarTopSubgeneroRequisicoes(subgeneroSelecionado);
                                apresentaTopLivros(livros);
                            }
                            dialog.dispose();
                        }
                    });

                    dialog.add(selecionarSubgeneroBtn, BorderLayout.SOUTH);
                    dialog.revalidate();
                    dialog.repaint();
                }
            }
        });

        dialog.add(selecionarGeneroBtn, BorderLayout.SOUTH);
        dialog.setSize(300, 400);
        dialog.setVisible(true);
    }
}
