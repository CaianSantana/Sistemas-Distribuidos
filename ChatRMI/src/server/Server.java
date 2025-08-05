package server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String[] args) {
		MessageRMIServer server = new MessageServer();
		try {
			MessageRMIServer stub = (MessageRMIServer) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Server", stub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
