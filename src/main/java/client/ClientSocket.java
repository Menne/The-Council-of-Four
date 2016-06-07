package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.controller.ClientController;
import client.view.socket.CLIsocket;
import modelDTO.GameDTO;

public class ClientSocket {

	private final static int PORT = 29999;
	private final String IP;
	
	public ClientSocket(String IP) {
		this.IP=IP;
	}
	
	public void startClient()
				throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		System.out.println("Connection created");
		
		
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		CLIsocket view=new CLIsocket(clientGame.getParser(), socket);
		clientGame.registerObserver(view);				
		view.registerObserver(clientController);
		ExecutorService executor=Executors.newSingleThreadExecutor();
		executor.submit(view);
		view.welcome();
		view.input();
	}
}
