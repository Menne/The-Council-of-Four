package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import client.controller.ClientController;
import client.view.rmi.RMIConnection;
import client.view.ClientView;
import client.view.rmi.ClientRMIViewRemote;
import modelDTO.GameDTO;
import server.view.RMIViewRemote;

public class ClientRMI{
	
	private final String HOST;
	private final static int PORT = 52365;
	private static final String NAME = "CoF";
	private final String clientName;
	
	public ClientRMI(String HOST, String clientName) {
		this.clientName=clientName;
		this.HOST=HOST;
	}
	
	public void startClient(String graphic) throws RemoteException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry(HOST, PORT);
		RMIViewRemote serverStub= (RMIViewRemote) registry.lookup(NAME);
		
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		RMIConnection connection=new RMIConnection(serverStub);
		ClientRMIViewRemote clientRMIViewRemote=(ClientRMIViewRemote) UnicastRemoteObject.exportObject(connection,0);	
		ClientView view;
		if("CLI".equals(graphic))
			view=new CLI(clientGame.getParser(), connection);
		else
			view=new GUI(connection);
		clientGame.registerObserver(view);
		connection.registerObserver(clientController);
		view.welcome(clientName);
		view.input();
	}

}
