package server;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import client.MessageRMIClient;

public class MessageServer implements MessageRMIServer {
	List<String> connectedUsers = new ArrayList<String>();

	@Override
	public void sendMessage(String sender, String message) throws RemoteException {
		for (String user : connectedUsers) {
			if(user.equals(sender))
				continue;
			
			Registry registry = LocateRegistry.getRegistry();
			MessageRMIClient client;
			try {
				client = (MessageRMIClient) registry.lookup(user);
				client.receiveMessage(sender + ": " + message);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void connect(String username) {
		connectedUsers.add(username);
	}
}
