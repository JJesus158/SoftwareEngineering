import Enum.ContactoPredefinido;
import Gestores.GestorSocios;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PagamentosMultas extends JFrame {
    private JTextField textoPesquisa;
    private JComboBox<String> btnFiltro;
    private JPanel listaSocios;
    private JPanel panel;
    private JButton btnVoltar;
    private JScrollPane scrollPane;
    private String searchTerm = "";

    public PagamentosMultas() {
        LinkedList<Socio> socios;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);
        listaSocios.setPreferredSize(new Dimension(600, 999999));

        btnFiltro.addItem("Geral");
        btnFiltro.addItem("Pago");
        btnFiltro.addItem("Em Divida");

        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTerm = textoPesquisa.getText();
                updateSociosList();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EcraPrincipal();
                dispose();
            }
        });
    }

    private void updateSociosList() {
        LinkedList<Socio> socios = switch (btnFiltro.getSelectedIndex()) {
            case 0 -> searchTerm.trim().isEmpty() ? GestorSocios.getInstance().mostrarTodosSocios() : GestorSocios.getInstance().pesquisarPorNome(searchTerm);
            case 1 -> searchTerm.trim().isEmpty() ? GestorSocios.getInstance().mostrarSociosPago() : GestorSocios.getInstance().pesquisarPorNomePago(searchTerm);
            case 2 -> searchTerm.trim().isEmpty() ? GestorSocios.getInstance().mostrarSociosEmDivida() : GestorSocios.getInstance().pesquisarPorNomeEmDivida(searchTerm);
            default -> null;
        };

        if (socios == null) {
            JOptionPane.showMessageDialog(panel, "Nenhum sócio encontrado.");
            return;
        }

        apresenta(socios);
    }

    private void apresenta(LinkedList<Socio> socios) {
        listaSocios.removeAll();
        boolean isEmDivida = btnFiltro.getSelectedIndex() == 2;
        for (Socio socio : socios) {
            String contact = socio.getContactoPredefinido().equals(ContactoPredefinido.EMAIL) ? socio.getEmail() : String.valueOf(socio.getTelefone());
            String textSocio = "<html>" + socio.getNome() + "<br>" + socio.getId() + "<br>" + contact + "<br>" + socio.getEstadoPagamento() + "</html>";
            JLabel lblSocio = new JLabel(textSocio);
            lblSocio.setPreferredSize(new Dimension(500, 100));
            lblSocio.setMaximumSize(new Dimension(500, 100));
            lblSocio.setHorizontalAlignment(SwingConstants.CENTER);
            lblSocio.setVerticalAlignment(SwingConstants.CENTER);

            JPanel panelSocio = new JPanel(new BorderLayout());
            panelSocio.setPreferredSize(new Dimension(200, 100));
            panelSocio.setMaximumSize(new Dimension(200, 100));
            panelSocio.add(lblSocio, BorderLayout.CENTER);
            panelSocio.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            panelSocio.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showSocioOptions(socio);
                }
            });

            listaSocios.add(panelSocio);
        }

        // Add the "Notifica" button at the bottom if the filter is "Em Divida"
        if (isEmDivida) {
            JButton btnNotifica = new JButton("Notificar");
            btnNotifica.addActionListener(new ActionListener() {
                /*codigo para notificaar os sócios em divida*/
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(panel, "Notificações enviadas para sócios em dívida.");
                }
            });

            JPanel bottomPanel = new JPanel();
            bottomPanel.setPreferredSize(new Dimension(500, 50));
            bottomPanel.add(btnNotifica);
            listaSocios.add(bottomPanel);
        }

        listaSocios.revalidate();
        listaSocios.repaint();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void showSocioOptions(Socio socio) {
        // Options for the popup dialog
        Object[] options = {"Ver Perfil", "Ver Histórico de Pagamentos/Notificar"};

        int result = JOptionPane.showOptionDialog(this,
                "Escolha uma ação:",
                "Info Socio Pagamentos",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (result == JOptionPane.YES_OPTION) {
            dispose();
            new InfoSocios(PagamentosMultas.this,socio);
        } else if (result == JOptionPane.NO_OPTION) {
            new HistoricoPagamento(socio);
            dispose();
        }
    }


}
