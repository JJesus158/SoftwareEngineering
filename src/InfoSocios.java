import Gestores.GestorSocios;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoSocios extends JDialog {
    private JPanel panel1;
    private JButton verPerfilButton;
    private JButton verHistóricoDeRequisiçõesButton;
    private JButton editarButton;
    private JButton removerButton;

    public InfoSocios(JFrame parent, Socio socio) {
        super(parent, "Info Socios", true); // Create a modal dialog
        setContentPane(panel1);
        setSize(300, 200); // Set the size of the dialog
        setLocationRelativeTo(parent); // Center the dialog relative to the parent

        verPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerfilSocio(socio);
                dispose();
            }
        });
        verHistóricoDeRequisiçõesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Historico(socio);
                dispose();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorSocios.getInstance().remover(socio);
                new Socios();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarSocio(socio);
                dispose();
            }
        });
    }
}
