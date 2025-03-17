import Gestores.GestorLivros;
import Gestores.GestorSocios;
import Modelos.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdicionarReserva extends JFrame
{
    private JPanel panel;
    private JTextField numSocio;
    private JButton btnConfirmar;
    private JButton btnVoltar;
    private JLabel detLivro;
    private JLabel data;


    public AdicionarReserva(Livro livro)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        detLivro.setText(livro.getTitulo() + " - " + livro.getAutores());
        data.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVoltar(livro);
            }
        });


        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConfirmar(livro);
            }
        });
    }

    private void btnConfirmar(Livro livro) {
        try {
            if (!numSocio.getText().isEmpty() && GestorSocios.getInstance().contem(Integer.parseInt(numSocio.getText())))
            {
                livro.efetuarReserva(GestorSocios.getInstance().pesquisarPorId(Integer.parseInt(numSocio.getText())));
                GestorLivros.getInstance().guardar();
            }
            else
            {
                apresentaMensagem();
            }
            new LivroDetalhes(livro);
            dispose();
        } catch (Exception ex){
            apresentaMensagem();
        }
    }

    private static void apresentaMensagem() {
        JOptionPane.showMessageDialog(null, "O sócio não existe");
    }

    private void btnVoltar(Livro livro) {
        new LivroDetalhes(livro);
        dispose();
    }
}
