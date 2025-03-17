import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcraPrincipal extends JFrame
{
    private JButton btnLivrosEReq;
    private JButton btnSocios;
    private JButton btnPagamentosMultas;
    private JButton btnHistRequisicoes;
    private JButton btnStats;
    private JButton btnConfig;
    private JButton btnSair;
    private JPanel panel;

    public EcraPrincipal()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setSize(800,600);
        setVisible(true);

        btnSair.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sair();
            }
        });

        btnLivrosEReq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLivrosEReq();
            }
        });
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConfig();
            }
        });

        btnHistRequisicoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoricoDeRequisicoes();
                dispose();
            }
        });

        btnPagamentosMultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PagamentosMultas();
                dispose();
            }
        });

        btnSocios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Socios();
                dispose();
            }
        });

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Estatisticas();
                dispose();
            }
        });
    }

    private void btnConfig() {
        new Configuracoes();
        dispose();
    }

    private void btnLivrosEReq() {
        new Aquisicoes();
        dispose();
    }

    private static void sair() {
        System.exit(0);
    }
}
