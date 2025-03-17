import Gestores.GestorConfiguracoes;
import Gestores.GestorLivros;
import Gestores.GestorSocios;
import Modelos.Fornecedor;
import Modelos.Genero;
import Modelos.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarLivro extends JFrame
{
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

    public AdicionarLivro() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        // preeche o seletor dos generos
        genre.addItem("Escolha um genero");
        for (String genero: Genero.INSTANCIA.getGeneros())
        {
            genre.addItem(genero);
        }


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

        genre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genre();
            }
        });
    }

    private void genre() {
        subgenre.removeAllItems();
        for (String subgenero : Genero.INSTANCIA.getSubgenerosPorGenero(genre.getItemAt(genre.getSelectedIndex())))
        {
            subgenre.addItem(subgenero);
        }
    }

    private void btnGuardar() {
        try {
            if (!titulo.getText().isEmpty() && !autor.getText().isEmpty() &&
                    !sala.getText().isEmpty() && !prateleira.getText().isEmpty() &&
                    !estante.getText().isEmpty() && !nif_fornecedor.getText().isEmpty() && !nome_fornecedor.getText().isEmpty() &&
                    !editora.getText().isEmpty() && !ano.getText().isEmpty() && !edicao.getText().isEmpty() &&
                    !isbn.getText().isEmpty() && !genre.getItemAt(genre.getSelectedIndex()).isEmpty() && !subgenre.getItemAt(subgenre.getSelectedIndex()).isEmpty())
            {
                Livro livro = new Livro(autor.getText(), titulo.getText(), editora.getText(),
                        isbn.getText(), genre.getItemAt(genre.getSelectedIndex()), subgenre.getItemAt(subgenre.getSelectedIndex()),
                        Short.parseShort(ano.getText()), Short.parseShort(sala.getText()), Short.parseShort(estante.getText()),
                        Short.parseShort(prateleira.getText()), Short.parseShort(edicao.getText()), new Fornecedor(nome_fornecedor.getText(), Integer.parseInt(nif_fornecedor.getText())));
                GestorLivros.getInstance().inserir(livro);
                new Aquisicoes();
                dispose();
            }
            else
            {
                apresentarMensagem();
            }

        }
        catch (Exception ex) {
            apresentarMensagem();
        }
    }

    private void btnVoltar() {
        new Aquisicoes();
        dispose();
    }

    private void apresentarMensagem() {
        JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos e corretos");
    }
}
