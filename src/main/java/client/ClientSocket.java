package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.ModelDTO.GameDTO;
import client.clientController.ClientController;
import client.clientView.CLI;
import client.clientView.notifies.WelcomeNotify;

public class ClientSocket {

	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";
	
	public void startClient(ClientController clientController)
				throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		System.out.println("Connection created");
		ExecutorService executor=Executors.newFixedThreadPool(2);
		executor.submit(new ClientOutHandler(
				new ObjectOutputStream(socket.getOutputStream()), clientController));
		executor.submit(new ClientInHandler(
				new ObjectInputStream(socket.getInputStream()), clientController));
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		ClientSocket client=new ClientSocket();
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		CLI view=new CLI(clientGame, clientGame.getParser());
		
		client.startClient(clientController);
		view.input();
		
		clientGame.notifyObserver(new WelcomeNotify());
	}
}
