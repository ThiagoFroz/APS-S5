package connection;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server extends Thread {

    private static final List<ClientConnection> clients =
            new CopyOnWriteArrayList<>();

    private final Socket connection;

    private BufferedReader reader;
    private BufferedWriter writer;

    private String currentUser;

    public Server(Socket connection) {

        this.connection = connection;

        setupConnection();
    }

    private void setupConnection() {

        try {

            reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream())
            );

        } catch (IOException e) {

            System.out.println("Erro ao configurar conexão.");
        }
    }

    @Override
    public void run() {

        try {

            currentUser = reader.readLine();

            if (currentUser == null || currentUser.isBlank()) {

                disconnect();

                return;
            }

            clients.add(new ClientConnection(currentUser, writer));

            broadcast("Text&" + currentUser + " conectado.");

            System.out.println(currentUser + " conectado.");

            listenMessages();

        } catch (Exception e) {

            System.out.println("Erro com cliente: " + currentUser);

        } finally {

            disconnect();
        }
    }

    private void listenMessages() throws IOException {

        String message;

        while ((message = reader.readLine()) != null) {

            if (message.equalsIgnoreCase(
                    "Text&Disconnect " + currentUser
            )) {
                break;
            }

            System.out.println(
                    currentUser + ": " + message
            );

            broadcast(message);
        }
    }

    private void broadcast(String message) {

        for (ClientConnection client : clients) {

            try {

                client.getWriter().write(message + "\r\n");

                client.getWriter().flush();

            } catch (IOException e) {

                System.out.println(
                        "Erro ao enviar mensagem para "
                        + client.getUsername()
                );
            }
        }
    }

    private void disconnect() {

        try {

            removeClient(currentUser);

            broadcast("Text&Usuário "
                    + currentUser
                    + " desconectado.");

            System.out.println(
                    currentUser + " desconectado."
            );

            if (reader != null) {
                reader.close();
            }

            if (writer != null) {
                writer.close();
            }

            if (connection != null
                    && !connection.isClosed()) {

                connection.close();
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao desconectar usuário."
            );
        }
    }

    private void removeClient(String username) {

        clients.removeIf(client ->
                client.getUsername()
                        .equals(username)
        );
    }
}