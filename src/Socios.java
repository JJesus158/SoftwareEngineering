import Gestores.GestorSocios;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Socios extends JFrame
{
    private JTextField textoPesquisa;
    private JComboBox<String> btnFiltro;
    private JPanel listaSocios;
    private JPanel panel;
    private JButton btnAdicionar;
    private JButton btnVoltar;
    private JScrollPane scrollPane;

    public Socios()
    {
        LinkedList<Socio> socios;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);
        listaSocios.setPreferredSize(new Dimension(600, 999999));

        btnFiltro.addItem("Todos");
        btnFiltro.addItem("Aluno");
        btnFiltro.addItem("Professor");
        btnFiltro.addItem("Funcionario");

        // mostra todos assim que abre o ecra
        socios = GestorSocios.getInstance().mostrarTodosSocios();
        apresenta(socios);

        textoPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = textoPesquisa.getText();
                LinkedList<Socio> resultadoPesquisa = new LinkedList<>();

                if (texto.matches("\\d+")) { // verifica se o texto é composto apenas por números
                    if (texto.length() == 9) { // se for 9 é nif
                        Socio socio = GestorSocios.getInstance().pesquisarPorNif(Integer.parseInt(texto));
                        if (socio != null) {
                            resultadoPesquisa.add(socio);
                        }
                    } else if (texto.length() < 9) { // se for inferior é id
                        Socio socio = GestorSocios.getInstance().pesquisarPorId(Integer.parseInt(texto));
                        if (socio != null) {
                            resultadoPesquisa.add(socio);
                        }
                    }
                } else {
                    resultadoPesquisa = GestorSocios.getInstance().pesquisarPorNome(texto);
                }

                apresenta(resultadoPesquisa);
            }
        });

        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Socio> socios = null;

                switch (btnFiltro.getSelectedIndex()){
                    case 0:
                        socios =  GestorSocios.getInstance().mostrarTodosSocios();
                        break;

                    case 1:
                        socios =  GestorSocios.getInstance().mostrarAlunos();
                        break;

                    case 2:
                        socios =  GestorSocios.getInstance().mostrarProfessores();
                        break;

                    case 3:
                        socios =  GestorSocios.getInstance().mostrarFuncionarios();
                }

                apresenta(socios);

            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EcraPrincipal();
                dispose();
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdicionarSocio();
                dispose();
            }
        });
    }

    private void apresenta(LinkedList<Socio> socios)
    {
        listaSocios.removeAll();
        for (Socio socio : socios) {
            JButton btnSocio = new JButton(socio.toString());
            btnSocio.setPreferredSize(new Dimension(150, 100));
            btnSocio.setMaximumSize(new Dimension(150, 100));
            //btnSocio.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnSocio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InfoSocios dialog = new InfoSocios(Socios.this, socio);
                    dialog.setVisible(true);
                }
            });
            listaSocios.add(btnSocio);

        }
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listaSocios.revalidate();
        listaSocios.repaint();
    }
}
