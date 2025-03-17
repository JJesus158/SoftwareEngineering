import Modelos.Livro;
import Modelos.Reserva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class Reservas extends JFrame{
    private JLabel tituloAutor;
    private JButton btnVoltar;
    private JPanel panel;
    private JPanel listaReservas;

    public Reservas(Livro livro) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        LinkedList<Reserva> reservas = livro.getReservas();

        tituloAutor.setText(livro.getTitulo() + " - " + livro.getAutores());

        apresenta(reservas);

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

    private void apresenta(LinkedList<Reserva> reservas)
    {
        listaReservas.removeAll();
        for (Reserva reserva : reservas) {
            JLabel label = new JLabel((reservas.indexOf(reserva) + 1) + " " + reserva.getSocio().getNome() + " - " + (new SimpleDateFormat("dd/MM/yyyy")).format(reserva.getData()));
            JButton btnRemover = new JButton("Remover");
            btnRemover.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnRemover(reserva);
                }
            });
            listaReservas.add(label);
            listaReservas.add(btnRemover);
        }
    }

    private void btnRemover(Reserva reserva) {
        reserva.getLivro().eliminarReserva(reserva);
        dispose();
        new Reservas(reserva.getLivro());
    }
}
