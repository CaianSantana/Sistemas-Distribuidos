package cliente;


import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Conecta-se ao servidor no IP da máquina e porta 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado ao servidor.");

           
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           
// USANDO A CLASSE INSTANT            
//            Instant tempoRequisicao;
//            Instant tempoResposta;

// USANDO A CLASSE TIMESTAMP
            Timestamp tempoRequisicao;
            Timestamp tempoResposta;
           
//            USANDO A CLASSE LOCALDATETIME
//            LocalTime tempoRequisicao;
//            LocalTime tempoResposta;

            // Envia mensagens até o usuário digitar "sair"
            String mensagem;
            while (true) {
                System.out.print("Digite uma mensagem (ou 'sair' para encerrar): ");
                mensagem = teclado.readLine();
                if ("sair".equalsIgnoreCase(mensagem)) {
                    break;
                }
               
                output.println(mensagem);
                tempoRequisicao = new Timestamp(System.currentTimeMillis());
//                tempoRequisicao = Instant.now();
//                tempoRequisicao = LocalTime.now();
//                System.out.println("Resposta do servidor: " + input.readLine());
               String ms = input.readLine();
               Timestamp tempoRespostaServidor = Cliente.converterMensagemServidor(ms);
               tempoResposta = new Timestamp(System.currentTimeMillis());
               Long tempoChegadaServidor = Long.decode(ms);
               

//                tempoResposta = Instant.now();
//                tempoResposta = LocalTime.now();
               
               
               
//                tempoResposta = Instant.now();
//                Instant resultado = new Instant(tempoResposta - tempoRequisicao);
               
//                long diff = tempoResposta.compareTo(tempoRequisicao);
//                long diff = ChronoUnit.MILLIS.between(tempoRequisicao, tempoResposta);
               
                long diff = tempoResposta.getTime() - tempoRequisicao.getTime();
               
                System.out.println("Tempo do envio da requisição: " + tempoRequisicao);
                System.out.println("Tempo da resposta do servidor: " + tempoRespostaServidor);
                System.out.println("Tempo da chegada da resposta: " +  tempoResposta);
                System.out.println("Valor do RTT: " + diff + " (ms)");
                Timestamp t = new Timestamp(tempoChegadaServidor + (diff / 2));
                System.out.println("Valor de T: " + t);
            }

            // Fecha as conexões
            input.close();
            output.close();
            teclado.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public static Timestamp converterMensagemServidor(String milissegundos) {
    Long ms = Long.decode(milissegundos);
    Timestamp timestamp = new Timestamp(ms);
    return timestamp;
    }
}
