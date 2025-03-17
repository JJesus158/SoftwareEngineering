import Gestores.GestorLivros;
import Modelos.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LivroDetalhes extends JFrame
{
    private JButton btnVoltar;
    private JButton btnRemover;
    private JButton btnEditar;
    private JButton btnFornecedor;
    private JButton btnFilaReservas;
    private JButton btnExemplares;
    private JButton btnReservar;
    private JLabel titulo;
    private JLabel autor;
    private JLabel editora;
    private JLabel genero;
    private JLabel subgenero;
    private JLabel ano;
    private JLabel isbn;
    private JLabel disponiveis;
    private JLabel total;
    private JLabel sala;
    private JLabel estante;
    private JLabel prateleira;
    private JLabel fornecedor;
    private JPanel panel;
    private JLabel edicao;


    public LivroDetalhes(Livro livro) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        apresenta(livro);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRemover(livro);
            }
        });

        btnFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFornecedor(livro);
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEditar(livro);
            }
        });
        btnFilaReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFilaReservas(livro);
            }
        });

        btnExemplares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnExemplares(livro);
            }
        });

        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReservar(livro);
            }
        });
    }

    private void btnReservar(Livro livro) {
        if (livro.getQuantidadeDisponiveis() > 0)
        {
            apresentaMensagem("Não é necessário reservar o livro, existem exemplares disponiveis");
        }
        else
        {
            new AdicionarReserva(livro);
            dispose();
        }
    }

    private void btnExemplares(Livro livro) {
        new Exemplares(livro);
        dispose();
    }

    private void btnFilaReservas(Livro livro) {
        new Reservas(livro);
        dispose();
    }

    private void btnEditar(Livro livro) {
        new EditarLivro(livro);
        dispose();
    }

    private void btnFornecedor(Livro livro) {
        new Fornecedor(livro);
        dispose();
    }

    private void btnRemover(Livro livro) {
        if (livro.getExemplares().isEmpty())
        {
            GestorLivros.getInstance().remover(livro);
            new Aquisicoes();
            dispose();
        }
        else
        {
            apresentaMensagem("Não é possivel remover livros que tenham exemplares requisitados.");
        }
    }

    private static void apresentaMensagem(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void btnVoltar() {
        new Aquisicoes();
        dispose();
    }

    private void apresenta(Livro livro)
    {
        titulo.setText(livro.getTitulo());
        autor.setText("Autor: " + livro.getAutores());
        editora.setText("Editora: " + livro.getEditora());
        edicao.setText("Edicao: " + livro.getEdicao());
        genero.setText("Género: " + livro.getGenero());
        subgenero.setText("Subgénero: " + livro.getSubGenero());
        ano.setText("Ano: " + livro.getAno());
        isbn.setText("ISBN: " + livro.getIsbn());
        disponiveis.setText("Quantidade disponiveis: " + String.valueOf(livro.getQuantidadeDisponiveis()));
        total.setText("Quantidade total: " + String.valueOf(livro.getQuantidadeTotal()));
        sala.setText("Sala: " + String.valueOf(livro.getSala()));
        estante.setText("Estante: " + String.valueOf(livro.getEstante()));
        prateleira.setText("Prateleira: " + String.valueOf(livro.getPrateleira()));
        fornecedor.setText("Fornecedor: " + livro.getFornecedor().getNome());
    }
}
