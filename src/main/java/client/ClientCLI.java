package client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.connections.ClientRMIViewRemote;
import client.connections.RMIConnection;
import client.connections.SocketConnection;
import client.controller.ClientController;
import client.modelDTO.GameDTO;
import client.view.CLI;
import client.view.ClientView;
import server.view.RMIViewRemote;


public class ClientCLI {
	
	private final static int PORT_RMI = 52365;
	private final static int PORT_SOCKET = 29999;
	private static final String NAME = "CoF";

	public static void main(String[] args) throws NotBoundException, UnknownHostException, IOException {
		String stringConnection="";
		String name="";
	
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to CoF, please enter your name!");
		name=scanner.nextLine();	
		
		System.out.println("Do you want to use Socket or RMI?");
		while(!"Socket".equals(stringConnection) && !"RMI".equals(stringConnection)){
			stringConnection=scanner.nextLine();
			if(!"Socket".equals(stringConnection) && !"RMI".equals(stringConnection))
				System.out.println("Wrong input. Try again.");
		}
		
		System.out.println("Please insert the server address (0 for localhost)");
		while(true){
			String ip=scanner.nextLine();
			try{
				if("RMI".equals(stringConnection)){
					Registry registry = LocateRegistry.getRegistry(ip, PORT_RMI);
					RMIViewRemote serverStub= (RMIViewRemote) registry.lookup(NAME);
					
					GameDTO clientGame=new GameDTO();
					ClientController clientController=new ClientController(clientGame);
					RMIConnection connection=new RMIConnection(serverStub);
					ClientRMIViewRemote clientRMIViewRemote=(ClientRMIViewRemote) UnicastRemoteObject.exportObject(connection,0);	
					ClientView view=new CLI(connection, clientGame);
					clientGame.registerObserver(view);
					connection.registerObserver(clientController);
					view.welcome(name);
					view.input();
					scanner.close();
					return;
				}
				else{
					Socket socket=new Socket(ip, PORT_SOCKET);
					System.out.println("Connection created");
										
					GameDTO clientGame=new GameDTO();
					ClientController clientController=new ClientController(clientGame);
					SocketConnection connection=new SocketConnection(socket);
					ClientView view=new CLI(connection, clientGame);
					clientGame.registerObserver(view);				
					connection.registerObserver(clientController);
					ExecutorService executor=Executors.newSingleThreadExecutor();
					executor.submit(connection);
					view.welcome(name);
					view.input();
					executor.shutdown();
					scanner.close();
					return;
				}
			}catch(SocketException | RemoteException e){
				System.out.println("Server Unreachable. Try again");
			}
		}
		
	}

}
