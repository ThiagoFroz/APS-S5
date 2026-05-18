package chatform;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    private static final long serialVersionUID =
            948747724372712259L;

    private JPanel contentPane;

    private JTextField inputIP;

    private JTextField inputPort;

    private JTextField inputUser;

    public Login() {

        configureWindow();

        createComponents();

        setVisible(true);
    }

    private void configureWindow() {

        setTitle("Aplicação de Conversa (Cliente)");

        setResizable(false);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(374, 277);

        setLocationRelativeTo(null);

        contentPane = new JPanel();

        contentPane.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

        contentPane.setLayout(null);

        setContentPane(contentPane);
    }

    private void createComponents() {

        JLabel lblServerIp =
                new JLabel("IP do servidor:");

        lblServerIp.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        lblServerIp.setBounds(
                5, 11, 151, 39
        );

        contentPane.add(lblServerIp);

        inputIP = new JTextField("192.168.0.21");

        inputIP.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        inputIP.setBounds(
                138, 15, 222, 30
        );

        contentPane.add(inputIP);

        JLabel lblPort =
                new JLabel("Porta do servidor:");

        lblPort.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        lblPort.setBounds(
                5, 72, 151, 39
        );

        contentPane.add(lblPort);

        inputPort = new JTextField("45454");

        inputPort.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        inputPort.setBounds(
                138, 76, 222, 30
        );

        contentPane.add(inputPort);

        JLabel lblUser =
                new JLabel("Nome de usuário:");

        lblUser.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        lblUser.setBounds(
                5, 134, 151, 39
        );

        contentPane.add(lblUser);

        inputUser = new JTextField();

        inputUser.setText("Thiago");

        inputUser.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        inputUser.setBounds(
                138, 138, 222, 30
        );

        contentPane.add(inputUser);

        JButton btnLogin =
                new JButton("Conectar");

        btnLogin.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        btnLogin.setBounds(
                5, 198, 355, 39
        );

        btnLogin.addActionListener(
                e -> connect()
        );

        contentPane.add(btnLogin);
    }

    private void connect() {

        String user = getUser();

        String ip = getIP();

        String port = getPort();

        if (user.isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite um nome de usuário."
            );

            return;
        }

        try {

            Integer.parseInt(port);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Porta inválida."
            );

            return;
        }

        try {

            new Client(
                    new String[]{
                            user,
                            ip,
                            port
                    }
            );

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Falha ao conectar."
            );
        }
    }

    private String getIP() {

        return inputIP.getText().trim();
    }

    private String getPort() {

        return inputPort.getText().trim();
    }

    private String getUser() {

        return inputUser.getText().trim();
    }
}