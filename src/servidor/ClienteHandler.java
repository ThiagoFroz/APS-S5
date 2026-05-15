package servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClienteHandler extends Thread {

    private Socket socket;

    private String nomeUsuario;

    private PrintWriter saida;

    private static ArrayList<ClienteHandler> clientes =
            new ArrayList<>();

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            saida =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            // recebe nome do usuário
            nomeUsuario = entrada.readLine();

            System.out.println(nomeUsuario + " entrou no chat.");

            clientes.add(this);

            enviarParaTodos(">> " + nomeUsuario + " entrou no chat.", this);

            String mensagem;

            while ((mensagem = entrada.readLine()) != null) {

                String mensagemFinal =
                        "[" + nomeUsuario + "]: " + mensagem;

                System.out.println(mensagemFinal);

                enviarParaTodos(mensagemFinal, this);
            }

        } catch (IOException e) {

            System.out.println(nomeUsuario + " saiu.");

        } finally {

            clientes.remove(this);

            enviarParaTodos(">> " + nomeUsuario + " saiu do chat.", this);
        }
    }

    // envia para todos MENOS para quem enviou
    private void enviarParaTodos(String mensagem,
                                 ClienteHandler remetente) {

        for (ClienteHandler cliente : clientes) {

            if (cliente != remetente) {

                cliente.saida.println(mensagem);
            }
        }
    }
}