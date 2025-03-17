import Gestores.GestorConfiguracoes;
import Gestores.GestorLivros;
import Gestores.GestorSocios;
import Modelos.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fornecedor extends JFrame {
    private JButton btnVoltar;
    private JLabel nome;
    private JLabel nif;
    private JPanel panel;


    public Fornecedor(Livro livro) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        nome.setText("Nome: " + livro.getFornecedor().getNome());
        nif.setText("NIF: " + livro.getFornecedor().getNif());

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar(livro);
            }
        });
    }

    private void btnVoltar(Livro livro) {
        new LivroDetalhes(livro);
        dispose();
    }
}
