import Gestores.GestorConfiguracoes;
import Gestores.GestorLivros;
import Gestores.GestorSocios;
import Modelos.Fornecedor;
import Modelos.Genero;
import Modelos.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarLivro extends JFrame {
    private JTextField autor;
    private JComboBox<String> genre;
    private JButton btnVoltar;
    private JButton btnGuardar;
    private JPanel panel;
    private JComboBox<String> subgenre;
    private JTextField titulo;
    private JTextField editora;
    private JTextField ano;
    private JTextField isbn;
    private JTextField sala;
    private JTextField prateleira;
    private JTextField estante;
    private JTextField nome_fornecedor;
    private JTextField nif_fornecedor;
    private JTextField edicao;


    public EditarLivro(Livro livro) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        apresenta(livro);

        genre.addItem("Escolha um genero");
        for (String genero: Genero.INSTANCIA.getGeneros())
        {
            genre.addItem(genero);
        }

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar(livro);
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardar(livro);
            }
        });
        genre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genre();
            }
        });
    }

    private void btnVoltar(Livro livro) {
        new LivroDetalhes(livro);
        dispose();
    }

    private void genre() {
        subgenre.removeAllItems();
        for (String subgenero : Genero.INSTANCIA.getSubgenerosPorGenero(genre.getItemAt(genre.getSelectedIndex())))
        {
            subgenre.addItem(subgenero);
        }
    }

    private void btnGuardar(Livro livro) {
        try {
            if (!titulo.getText().isEmpty() && !autor.getText().isEmpty() &&
                    !sala.getText().isEmpty() && !prateleira.getText().isEmpty() &&
                    !estante.getText().isEmpty() && !nif_fornecedor.getText().isEmpty() && !nome_fornecedor.getText().isEmpty() &&
                    !editora.getText().isEmpty() && !ano.getText().isEmpty() && !edicao.getText().isEmpty() &&
                    !isbn.getText().isEmpty() && !genre.getItemAt(genre.getSelectedIndex()).isEmpty() && !subgenre.getItemAt(subgenre.getSelectedIndex()).isEmpty())
            {
                livro.atualizar(autor.getText(), titulo.getText(), editora.getText(),
                        isbn.getText(), genre.getItemAt(genre.getSelectedIndex()), subgenre.getItemAt(subgenre.getSelectedIndex()),
                        Short.parseShort(ano.getText()), Short.parseShort(sala.getText()), Short.parseShort(estante.getText()),
                        Short.parseShort(prateleira.getText()), Short.parseShort(edicao.getText()));
                livro.getFornecedor().atualizar(nome_fornecedor.getText(), Integer.parseInt(nif_fornecedor.getText()));
                GestorLivros.getInstance().guardar();
                new LivroDetalhes(livro);
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
        JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos e corretos");
    }

    private void apresenta(Livro livro) {
        titulo.setText(livro.getTitulo());
        autor.setText(livro.getAutores());
        editora.setText(livro.getEditora());
        edicao.setText(String.valueOf(livro.getEdicao()));
        ano.setText(String.valueOf(livro.getAno()));
        isbn.setText(livro.getIsbn());
        sala.setText(String.valueOf(livro.getSala()));
        estante.setText(String.valueOf(livro.getEstante()));
        prateleira.setText(String.valueOf(livro.getPrateleira()));
        nome_fornecedor.setText(livro.getFornecedor().getNome());
        nif_fornecedor.setText(String.valueOf(livro.getFornecedor().getNif()));
    }
}
