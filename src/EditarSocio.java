import Gestores.GestorSocios;
import Modelos.Socio;
import Enum.ContactoPredefinido;
import Enum.TipoSocio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarSocio extends JFrame {
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

    public EditarSocio(Socio socio) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        apresenta(socio);

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
                        socio.editarSocio(
                                nome.getText(),
                                Integer.parseInt(idade.getText()),
                                Integer.parseInt(cc.getText()),
                                Integer.parseInt(nif.getText()),
                                email.getText(),
                                Integer.parseInt(telefone.getText()),
                                (ContactoPredefinido) contacto.getSelectedItem(),
                                morada.getText(),
                                (TipoSocio) tipo.getSelectedItem()
                        );
                        GestorSocios.getInstance().guardar();
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

    private void apresenta(Socio socio) {
        nome.setText(String.valueOf(socio.getNome()));
        idade.setText(String.valueOf(socio.getIdade()));
        cc.setText(String.valueOf(socio.getCC()));
        nif.setText(String.valueOf(socio.getNif()));
        email.setText(String.valueOf(socio.getEmail()));
        telefone.setText(String.valueOf(socio.getTelefone()));
        morada.setText(String.valueOf(socio.getMorada()));
    }
}

