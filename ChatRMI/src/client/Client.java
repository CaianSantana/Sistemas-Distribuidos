package client;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.MessageRMIServer;

public class Client {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Digite seu nome de usuário: ");
		String username = s.nextLine();
		try {
			MessageRMIClient rmiClient = new MessageClient();
			MessageRMIClient stub = (MessageRMIClient) UnicastRemoteObject.exportObject(rmiClient, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(username, stub);
			
			
			MessageRMIServer rmiServer = (MessageRMIServer) registry.lookup("Server");
			rmiServer.connect(username);
			System.out.println("Bem vindo " + username + " ao Chat-RMI!");
			System.out.print("Digite mensagens que quer enviar para outros usuários \n\n");
			while(true) {
			
				String message = s.nextLine();
				rmiServer.sendMessage(username, message);
			}
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		s.close();
	}
}
