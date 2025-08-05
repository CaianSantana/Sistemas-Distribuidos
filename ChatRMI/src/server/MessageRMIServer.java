package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageRMIServer extends Remote {
	public void sendMessage(String sender, String message) throws RemoteException;
	public void connect(String registryName)  throws RemoteException;
}
