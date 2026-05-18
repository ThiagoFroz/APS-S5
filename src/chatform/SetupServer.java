package chatform;

import connection.Server;

import java.awt.CardLayout;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class SetupServer extends JFrame {

    private static final long serialVersionUID = 4998717362394143017L;

    private JPanel contentPane;

    private JTextField inputPort;

    private JLabel lblValueIP;
    private JLabel lblValuePort;

    private ServerSocket server;

    private volatile boolean running = false;

    public SetupServer() {

        configureWindow();
        createComponents();

        setVisible(true);
    }

    private void configureWindow() {

        setTitle("Aplicação de Conversa (Servidor)");
        setSize(366, 158);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel(new CardLayout());

        setContentPane(contentPane);
    }

    private void createComponents() {

        JPanel panelConfig = createConfigPanel();
        JPanel panelStatus = createStatusPanel();

        contentPane.add(panelConfig, "config");
        contentPane.add(panelStatus, "status");
    }

    private JPanel createConfigPanel() {

        JPanel panel = new JPanel(null);

        JLabel lblPort = new JLabel("Número da porta:");
        lblPort.setBounds(10, 35, 140, 18);

        panel.add(lblPort);

        inputPort = new JTextField("45454");
        inputPort.setBounds(135, 35, 86, 20);

        panel.add(inputPort);

        JButton btnOk = new JButton("Iniciar");
        btnOk.setBounds(90, 86, 90, 23);

        btnOk.addActionListener(e -> startServer());

        panel.add(btnOk);

        JButton btnBack = new JButton("Voltar");
        btnBack.setBounds(190, 86, 90, 23);

        btnBack.addActionListener(e -> {
            dispose();
            new Start();
        });

        panel.add(btnBack);

        return panel;
    }

    private JPanel createStatusPanel() {

        JPanel panel = new JPanel(null);

        JLabel lblIp = new JLabel("IP:");
        lblIp.setBounds(38, 11, 46, 14);

        panel.add(lblIp);

        JLabel lblPort = new JLabel("Porta:");
        lblPort.setBounds(38, 36, 46, 14);

        panel.add(lblPort);

        lblValueIP = new JLabel();
        lblValueIP.setBounds(67, 11, 200, 14);

        panel.add(lblValueIP);

        lblValuePort = new JLabel();
        lblValuePort.setBounds(80, 36, 200, 14);

        panel.add(lblValuePort);

        JButton btnStop = new JButton("Encerrar");

        btnStop.setBounds(94, 79, 144, 30);

        btnStop.addActionListener(e -> stopServer());

        panel.add(btnStop);

        return panel;
    }

    private void startServer() {

        try {

            int port = Integer.parseInt(inputPort.getText());

            server = new ServerSocket(port);

            running = true;

            lblValueIP.setText(getLocalIP());
            lblValuePort.setText(String.valueOf(port));

            CardLayout layout = (CardLayout) contentPane.getLayout();
            layout.show(contentPane, "status");

            new Thread(this::acceptConnections).start();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Porta inválida!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Erro ao iniciar servidor:\n" + e.getMessage()
            );
        }
    }

    private void acceptConnections() {

        while (running && !server.isClosed()) {

            try {

                System.out.println("Waiting connection...");

                Socket connection = server.accept();

                Server serverThread = new Server(connection);

                serverThread.start();

            } catch (Exception e) {

                if (running) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void stopServer() {

        try {

            running = false;

            if (server != null && !server.isClosed()) {
                server.close();
            }

            JOptionPane.showMessageDialog(
                this,
                "Servidor encerrado."
            );

            dispose();

            new Start();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String getLocalIP() {

        try {

            return InetAddress
                    .getLocalHost()
                    .getHostAddress();

        } catch (Exception e) {

            return "IP não encontrado";
        }
    }
}