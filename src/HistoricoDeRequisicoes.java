import Gestores.GestorRequisicoes;
import Modelos.Exemplar;
import Modelos.Requisicao;
import Modelos.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class HistoricoDeRequisicoes extends JFrame {
    private JTextField textoPesquisa;
    private JButton btnVoltar;
    private JComboBox<String> btnFiltro;
    private JScrollPane scrollPane;
    private JPanel listaRequisicoes;
    private JPanel panel;
    private JButton btnNotificar;

    public HistoricoDeRequisicoes() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800, 600);
        setVisible(true);

        // Add filter options to the JComboBox
        btnFiltro.addItem("Geral");
        btnFiltro.addItem("Codigo de Socio");
        btnFiltro.addItem("Codigo de Exemplar");
        btnFiltro.addItem("Nome Do Livro");
        btnFiltro.addItem("Data de Requisição");

        // Set up the action listener for the "Voltar" button
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EcraPrincipal();
                dispose();
            }
        });

        // Set up the action listener for the filter JComboBox
        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFilter = (String) btnFiltro.getSelectedItem();
                String searchTerm = textoPesquisa.getText();

                if (!selectedFilter.equals("Geral") && (searchTerm == null || searchTerm.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(panel, "Please enter a search term.", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                LinkedList<Requisicao> requisicoes = null;
                switch (selectedFilter) {
                    case "Geral":
                        requisicoes = GestorRequisicoes.getInstance().mostrarTodasRequisicoes();
                        break;
                    case "Codigo de Socio":
                        requisicoes = GestorRequisicoes.getInstance().mostrarRequisicoesPorCodigoDeSocio(searchTerm);
                        break;
                    case "Codigo de Exemplar":
                        requisicoes = GestorRequisicoes.getInstance().mostrarRequisicoesPorCodigoDeExemplar(searchTerm);
                        break;
                    case "Nome Do Livro":
                        requisicoes = GestorRequisicoes.getInstance().mostrarRequisicoesPorNomeDoLivro(searchTerm);
                        break;
                    case "Data de Requisição":
                        requisicoes = GestorRequisicoes.getInstance().mostrarRequisicoesPorDataDeRequisicao(searchTerm);
                        break;
                    default:
                        break;
                }

                if (requisicoes != null) {
                    atualizarListaRequisicoes(requisicoes);
                }else {
                    JOptionPane.showMessageDialog(panel, "Nenhuma Requisição encontrada.", "Nenhuma Requisição encontrada", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void atualizarListaRequisicoes(LinkedList<Requisicao> requisicoes) {
        listaRequisicoes.removeAll();
        listaRequisicoes.setLayout(new BoxLayout(listaRequisicoes, BoxLayout.Y_AXIS));

        for (Requisicao requisicao : requisicoes) {
            JPanel requisicaoPanel = new JPanel();
            Socio socio = requisicao.getSocio();
            Exemplar exemplar= requisicao.getExemplar();

            requisicaoPanel.setLayout(new GridLayout(1, 4)); // Adjust based on your Requisicao object

            requisicaoPanel.add(new JLabel("Socio: #" + socio.getId()));
            requisicaoPanel.add(new JLabel("Data Inicio: " + requisicao.getDataInicio()));
            requisicaoPanel.add(new JLabel("Data Fim: " + requisicao.getDataFim()));
            requisicaoPanel.add(new JLabel("Livro: " + exemplar.getLivro().getTitulo()));
            requisicaoPanel.add(new JLabel("cod Exemplar: " + exemplar.getCodigo()));

            listaRequisicoes.add(requisicaoPanel);
        }

        listaRequisicoes.revalidate();
        listaRequisicoes.repaint();
    }

}
