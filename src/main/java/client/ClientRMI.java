package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import client.controller.ClientController;
import client.view.rmi.CLIrmi;
import client.view.rmi.ClientRMIViewRemote;
import modelDTO.GameDTO;
import server.view.RMIViewRemote;

public class ClientRMI{
	
	private final String HOST;
	private final static int PORT = 52365;
	private static final String NAME = "CoF";
	
	public ClientRMI(String HOST) {
		this.HOST=HOST;
	}
	
	public void startClient() throws RemoteException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry(HOST, PORT);
		RMIViewRemote serverStub= (RMIViewRemote) registry.lookup(NAME);
		
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		CLIrmi view=new CLIrmi(clientGame.getParser(), serverStub);
		ClientRMIViewRemote clientRMIViewRemote=(ClientRMIViewRemote) 
				UnicastRemoteObject.exportObject(view,0);
		clientGame.registerObserver(view);
		view.registerObserver(clientController);
		view.welcome();
		view.input();
	}

}
