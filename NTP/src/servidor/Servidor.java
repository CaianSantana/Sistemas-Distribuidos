package servidor;


import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Cria um servidor que escuta na porta 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado, aguardando conexão...");

            // Aceita a conexão de um cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            // Cria streams para comunicação com o cliente
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
           
            LocalDateTime localDateTime;
            Timestamp timestamp;
            Instant instant;

            // Recebe e envia mensagens
            String mensagem;
            while ((mensagem = input.readLine()) != null) {
                System.out.println("Mensagem recebida: " + mensagem);
                localDateTime = LocalDateTime.now();
                timestamp = new Timestamp(System.currentTimeMillis());
                instant = Instant.now();
                Servidor.setTimeout(null);
                output.println(System.currentTimeMillis());
            }

            // Fecha as conexões
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public static void setTimeout(Long milissegundos) {
   
    long ms = milissegundos == null ? 5000 : milissegundos;
   
    try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("A thread foi interrompida.");
        }
   
    }
}