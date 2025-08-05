package client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageRMIClient extends Remote {
	public void receiveMessage(String message) throws RemoteException;
}
