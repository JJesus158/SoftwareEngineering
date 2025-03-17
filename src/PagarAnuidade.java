import Gestores.GestorConfiguracoes;
import Modelos.Socio;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagarAnuidade extends JFrame {
    private JButton pagarButton;
    private JButton voltarButton;
    private JLabel validade;
    private JLabel id;
    private JLabel data;
    private JLabel valor;
    private JPanel panel;

    public PagarAnuidade(Socio socio) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        if (socio.isAluno()) {
            validade.setText("Validade da anuidade: " + GestorConfiguracoes.getInstance().getValorAnuidadeAluno());
            valor.setText("Valor: " + GestorConfiguracoes.getInstance().getValorAnuidadeAluno());
        }
        else if (socio.isFuncionario()){
            validade.setText("Validade da anuidade: " + GestorConfiguracoes.getInstance().getValorAnuidadeFuncionario());
            valor.setText("Valor: " + GestorConfiguracoes.getInstance().getValorAnuidadeFuncionario());
        }
        else{
            validade.setText("Validade da anuidade: " + GestorConfiguracoes.getInstance().getValorAnuidadeProfessor());
            valor.setText("Valor: " + GestorConfiguracoes.getInstance().getValorAnuidadeProfessor());
        }
        id.setText("Nr Sócio: " + socio.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdf.format(new Date());
        data.setText("Data atual do pagamento da anuidade: " + dataAtual);


        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerfilSocio(socio);
                dispose();
            }
        });

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                socio.pagarAnuidade();
                new PerfilSocio(socio);
                dispose();
            }
        });
    }
}
