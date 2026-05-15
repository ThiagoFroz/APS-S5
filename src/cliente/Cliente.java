package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        try {

            Socket socket = new Socket("192.168.0.21", 12345);

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter saida =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            // envia nome para o servidor
            saida.println(nome);

            Thread receberMensagem = new Thread(() -> {

                try {

                    String msg;

                    while ((msg = entrada.readLine()) != null) {

                        System.out.println(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receberMensagem.start();

            while (true) {

                String mensagem = scanner.nextLine();

                saida.println(mensagem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}