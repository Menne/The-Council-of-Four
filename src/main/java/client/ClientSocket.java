package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.controller.ClientController;
import client.view.ClientView;
import client.view.socket.SocketConnection;
import modelDTO.GameDTO;

public class ClientSocket {

	private final static int PORT = 29999;
	private final String IP;
	private final String clientName;
	
	public ClientSocket(String IP, String clientname) {
		this.IP=IP;
		this.clientName=clientname;
	}
	
	public void startClient(String graphic)
				throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		System.out.println("Connection created");
		
		
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		SocketConnection connection=new SocketConnection(socket);
		ClientView view;
		if("CLI".equals(graphic))
			view=new CLI(connection, clientGame);
		else
			view=new GUI(connection, clientGame);
		clientGame.registerObserver(view);				
		connection.registerObserver(clientController);
		ExecutorService executor=Executors.newSingleThreadExecutor();
		executor.submit(connection);
		view.welcome(clientName);
		view.input();
	}
}
