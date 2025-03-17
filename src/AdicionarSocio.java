import Gestores.GestorSocios;
import Modelos.Socio;
import Enum.ContactoPredefinido;
import Enum.TipoSocio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarSocio extends JFrame {
    private JButton btnVoltar;
    private JButton btnGuardar;
    private JPanel panel;
    private JTextField nome;
    private JTextField idade;
    private JTextField cc;
    private JTextField email;
    private JTextField telefone;
    private JTextField morada;
    private JTextField nif;
    private JComboBox<ContactoPredefinido> contacto;
    private JComboBox<TipoSocio> tipo;

    public AdicionarSocio() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);

        // Adiciona as opções da enumeração ContactoPredefinido ao JComboBox contacto
        for (ContactoPredefinido c : ContactoPredefinido.values()) {
            contacto.addItem(c);
        }

        // Adiciona as opções da enumeração TipoSocio ao JComboBox tipo
        for (TipoSocio t : TipoSocio.values()) {
            tipo.addItem(t);
        }

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Socios();
                dispose();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (    !nome.getText().isEmpty() && !idade.getText().isEmpty() &&
                            !cc.getText().isEmpty() && !nif.getText().isEmpty() &&
                            !email.getText().isEmpty() && !telefone.getText().isEmpty() &&
                            !morada.getText().isEmpty()) {
                        Socio socio = new Socio(
                                nome.getText(),
                                Integer.parseInt(idade.getText()),
                                Integer.parseInt(cc.getText()),
                                Integer.parseInt(nif.getText()),
                                email.getText(),
                                Integer.parseInt(telefone.getText()),
                                morada.getText(),
                                (TipoSocio) tipo.getSelectedItem(),
                                (ContactoPredefinido) contacto.getSelectedItem()
                        );
                        GestorSocios.getInstance().inserir(socio);
                        new Socios();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos e corretos");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos e corretos");
                }
            }
        });
    }
}
