package client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.controller.ClientController;
import client.view.notifies.WelcomeNotify;
import client.view.socket.CLI;
import modelDTO.GameDTO;

public class ClientSocket {

	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";
	
	public void startClient()
				throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		System.out.println("Connection created");
		
		GameDTO clientGame=new GameDTO();
		CLI view=new CLI(clientGame.getParser());
		clientGame.registerObserver(view);
		
		ExecutorService executor=Executors.newFixedThreadPool(2);
		ClientOutHandler clientOutHandler=new ClientOutHandler(
				new ObjectOutputStream(socket.getOutputStream()));
		executor.submit(clientOutHandler);

		ClientController clientController=new ClientController(clientGame, clientOutHandler);
		view.registerObserver(clientController);
		executor.submit(new ClientInHandler(
				new ObjectInputStream(socket.getInputStream()), clientController));
		
		clientGame.notifyObserver(new WelcomeNotify());
		view.input();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		ClientSocket client=new ClientSocket();
		client.startClient();
				
	}
}
