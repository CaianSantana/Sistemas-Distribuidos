package client;

public class MessageClient implements MessageRMIClient {

	@Override
	public void receiveMessage(String message)  {
		System.out.println(message);
	}

}
