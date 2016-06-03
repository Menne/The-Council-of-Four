package client.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.controller.ClientController;
import modelDTO.GameDTO;
import server.view.RMIViewRemote;

public class ClientRMI {
	
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 52365;
	private static final String NAME = "prigionieri";
	
	public void startClient() throws RemoteException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry(HOST, PORT);
		RMIViewRemote serverStub= (RMIViewRemote) registry.lookup(NAME);
		System.out.println("RMI connection created");
		
		GameDTO clientGame=new GameDTO();
		ClientController clientController=new ClientController(clientGame);
		
		
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		ClientRMI clientRMI=new ClientRMI();
		clientRMI.startClient();

	}

}
