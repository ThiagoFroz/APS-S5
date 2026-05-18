package chatform;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.*;

import java.net.ConnectException;
import java.net.Socket;

public class Client extends JFrame {

    private static final long serialVersionUID = 5391582161763137020L;

    private Socket socket;

    private BufferedWriter writer;

    private BufferedReader reader;

    private String user;

    private String serverIP;

    private int serverPort;

    private JTextField inputText;

    private JTextArea output;

    public Client(String[] args) {

        setClientInfo(args[0], args[1], args[2]);

        configureWindow();

        createComponents();

        connect();

        startListener();

        setVisible(true);
    }

    private void configureWindow() {

        setTitle("Chat - " + user);

        setSize(600, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                disconnect();

                new Start();
            }
        });
    }

    private void createComponents() {

        JPanel contentPane = new JPanel(new BorderLayout());

        contentPane.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

        setContentPane(contentPane);

        output = new JTextArea();

        output.setEditable(false);

        output.setLineWrap(true);

        JScrollPane messages =
                new JScrollPane(output);

        messages.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        );

        DefaultCaret caret =
                (DefaultCaret) output.getCaret();

        caret.setUpdatePolicy(
                DefaultCaret.ALWAYS_UPDATE
        );

        contentPane.add(messages, BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        contentPane.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        inputText = new JTextField();

        inputText.addActionListener(
                e -> sendCurrentMessage()
        );

        bottomPanel.add(
                inputText,
                BorderLayout.CENTER
        );

        JPanel buttons =
                new JPanel(new BorderLayout());

        bottomPanel.add(
                buttons,
                BorderLayout.EAST
        );

        JButton btnSend =
                new JButton("Enviar");

        btnSend.addActionListener(
                e -> sendCurrentMessage()
        );

        buttons.add(btnSend, BorderLayout.NORTH);

        JButton btnDisconnect =
                new JButton("Desconectar");

        btnDisconnect.addActionListener(
                e -> disconnect()
        );

        buttons.add(
                btnDisconnect,
                BorderLayout.SOUTH
        );
    }

    private void setClientInfo(
            String user,
            String serverIP,
            String serverPort
    ) {

        this.user = user;

        this.serverIP = serverIP;

        this.serverPort =
                Integer.parseInt(serverPort);
    }

    private void connect() {

        try {

            socket = new Socket(
                    serverIP,
                    serverPort
            );

            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()
                    )
            );

            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            writer.write(user + "\r\n");

            writer.flush();

            writeOutput(
                    "Conectado ao servidor."
            );

        } catch (ConnectException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Servidor indisponível."
            );

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao conectar."
            );

            dispose();
        }
    }

    private void startListener() {

        new Thread(this::listenMessages).start();
    }

    private void listenMessages() {

        try {

            String message;

            while ((message = reader.readLine())
                    != null) {

                processMessage(message);
            }

        } catch (Exception e) {

            writeOutput(
                    "Conexão encerrada."
            );
        }
    }

    private void processMessage(String message) {

        try {

            String[] parts =
                    message.split("&", 2);

            if (parts.length < 2) {

                writeOutput(
                        "Mensagem inválida."
                );

                return;
            }

            String command = parts[0];

            String text = parts[1];

            if (command.equals("Text")) {

                writeOutput(text);

            } else {

                writeOutput(
                        "Comando desconhecido."
                );
            }

        } catch (Exception e) {

            writeOutput(
                    "Erro ao processar mensagem."
            );
        }
    }

    private void sendCurrentMessage() {

        String text = inputText.getText().trim();

        if (text.isEmpty()) {
            return;
        }

        sendMessage(
                "Text&[" + user + "] ~> "
                        + text
        );

        inputText.setText("");
    }

    private void sendMessage(String message) {

        try {

            writer.write(message + "\r\n");

            writer.flush();

        } catch (Exception e) {

            writeOutput(
                    "Falha ao enviar mensagem."
            );
        }
    }

    private void writeOutput(String text) {

        SwingUtilities.invokeLater(() ->
                output.append(text + "\n")
        );
    }

    private void disconnect() {

        try {

            sendMessage(
                    "Text&Disconnect "
                            + user
            );

            if (writer != null) {
                writer.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (socket != null
                    && !socket.isClosed()) {

                socket.close();
            }

            writeOutput("Desconectado.");

            dispose();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao desconectar."
            );
        }
    }
}