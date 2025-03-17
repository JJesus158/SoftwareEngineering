import Modelos.Socio;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerfilSocio extends JFrame {
    private JButton voltarButton;
    private JButton btnPagar;
    private JButton btnLivros;
    private JLabel data;
    private JPanel panel;
    private JLabel nome;
    private JLabel cc;
    private JLabel idade;
    private JLabel nif;
    private JLabel contacto;
    private JLabel email;
    private JLabel telefone;
    private JLabel morada;
    private JLabel id;

    public PerfilSocio(Socio socio) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        id.setText("Pefil do sócio: " + socio.getId());

        nome.setText("Nome: " + socio.getNome());
        idade.setText("Idade: " + socio.getIdade());
        nif.setText("Nif: " + socio.getNif());
        cc.setText("CC: " + socio.getCC());

        contacto.setText("Contacto predefinido: " + socio.getContactoPredefinido());
        email.setText("Email: " + socio.getEmail());
        telefone.setText("Telefone: " + socio.getTelefone());

        morada.setText("Morada: " + socio.getMorada());

        data.setText("Data de expiração da anuidade: " + socio.getExpiracaoAnuidade());

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Socios();
                dispose();
            }
        });

        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PagarAnuidade(socio);
                dispose();
            }
        });

        btnLivros.setText("Livros requisitados: " + socio.getnRequisicoesAtuais());
        btnLivros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LivrosAtuais(socio);
                dispose();
            }
        });
    }
}