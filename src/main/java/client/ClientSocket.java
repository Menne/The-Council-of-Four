package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.connections.SocketConnection;
import client.controller.ClientController;
import client.modelDTO.GameDTO;
import client.view.CLI;
import client.view.ClientView;
import client.view.GUI;

public class ClientSocket {

	private final static int PORT = 29999;
	private final String IP;
	private final String clientName;
	
	public ClientSocket(String IP, String clientname) {
		this.IP=IP;
		this.clientName=clientname;
	}
	
	public void startClient(Object parameter)
				throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		System.out.println("Connection created");
		
		
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		SocketConnection connection=new SocketConnection(socket);
		ClientView view;
		if(!(parameter instanceof ControllerGUI))
			view=new CLI(connection, clientGame);
		else
			view=new GUI(connection, clientGame, (ControllerGUI) parameter);
		clientGame.registerObserver(view);				
		connection.registerObserver(clientController);
		ExecutorService executor=Executors.newSingleThreadExecutor();
		executor.submit(connection);
		view.welcome(clientName);
		view.input();
	}
}
