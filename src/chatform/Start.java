package chatform;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Start extends JDialog {

    private static final long serialVersionUID = 5602644222765201727L;

    private final JPanel contentPanel = new JPanel();

    public Start() {

        configureWindow();
        createComponents();

        setVisible(true);
    }

    private void configureWindow() {

        setTitle("Aplicação de Conversa");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getContentPane().setLayout(null);

        contentPanel.setBounds(0, 0, 434, 261);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);

        getContentPane().add(contentPanel);
    }

    private void createComponents() {

        JLabel lblInicializar = new JLabel("Como deseja inicializar o programa?");
        lblInicializar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblInicializar.setBounds(97, 40, 250, 34);

        contentPanel.add(lblInicializar);

        JButton btnServidor = new JButton("Servidor");
        btnServidor.setBounds(10, 126, 182, 61);

        btnServidor.addActionListener(e -> openServer());

        contentPanel.add(btnServidor);

        JButton btnCliente = new JButton("Cliente");
        btnCliente.setBounds(242, 126, 182, 61);

        btnCliente.addActionListener(e -> openClient());

        contentPanel.add(btnCliente);
    }

    private void openServer() {

        new SetupServer().setVisible(true);
        dispose();
    }

    private void openClient() {

        Login login = new Login();

        login.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                dispose();
            }
        });

        login.setVisible(true);
    }
}