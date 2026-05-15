package cliente;

import java.awt.*;
import javax.swing.*;

public class TelaChat extends JFrame {

    private JTextArea areaChat;
    private JTextField campoMensagem;
    private JButton botaoEnviar;

    public TelaChat() {

        setTitle("Chat Ambiental");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaChat = new JTextArea();
        areaChat.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaChat);

        campoMensagem = new JTextField();

        botaoEnviar = new JButton("Enviar");

        JPanel painelInferior = new JPanel(new BorderLayout());

        painelInferior.add(campoMensagem, BorderLayout.CENTER);
        painelInferior.add(botaoEnviar, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {

        new TelaChat();
    }
}