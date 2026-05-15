package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        try {

            ServerSocket servidor = new ServerSocket(12345);

            System.out.println("Servidor iniciado...");

            while (true) {

                Socket socket = servidor.accept();

                System.out.println("Novo cliente conectado!");

                ClienteHandler cliente =
                        new ClienteHandler(socket);

                cliente.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}