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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.connections.ClientRMIViewRemote;
import client.connections.RMIConnection;
import client.connections.SocketConnection;
import client.controller.ClientController;
import client.modelDTO.GameDTO;
import client.view.ClientView;
import client.view.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import server.view.RMIViewRemote;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class ControllerLogin {
	
	private final static int PORT_RMI = 52365;
	private final static int PORT_SOCKET = 29999;
	private static final String NAME = "CoF";

	@FXML
	private TextField name;
	
	@FXML
	private ToggleGroup connection;
	
	@FXML
	private Toggle socket;
	
	@FXML
	private Toggle rmi;
	
	@FXML
	private TextField address;

	private ClientGUI clientGUI;
	
	
	public void setClientGUI(ClientGUI clientGUI) {
		this.clientGUI = clientGUI;
	}



	public void play() throws UnknownHostException, IOException, NotBoundException{
		Alert alert=new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		if(name.getText().isEmpty() || address.getText().isEmpty()){
			alert.setHeaderText("Error, you must complete all the fields!");
			alert.showAndWait();
			return;
		}
		while(true){
			try {
				if(connection.getSelectedToggle().equals(socket)){

					Socket socket=new Socket(address.getText(), PORT_SOCKET);
					System.out.println("Connection created");
					
					GameDTO clientGame=new GameDTO();
					ClientController clientController=new ClientController(clientGame);
					SocketConnection connection=new SocketConnection(socket);
					ControllerGUI controllerGUI=clientGUI.showGame();
					ClientView view=new GUI(connection, clientGame, controllerGUI);
					clientGame.registerObserver(view);				
					connection.registerObserver(clientController);
					ExecutorService executor=Executors.newSingleThreadExecutor();
					executor.submit(connection);
					view.welcome(name.getText());
					view.input();				
					break;
				}
				else{
					Registry registry = LocateRegistry.getRegistry(address.getText(), PORT_RMI);
					RMIViewRemote serverStub= (RMIViewRemote) registry.lookup(NAME);
					
					GameDTO clientGame=new GameDTO();
					ClientController clientController=new ClientController(clientGame);
					RMIConnection connection=new RMIConnection(serverStub);
					ClientRMIViewRemote clientRMIViewRemote=(ClientRMIViewRemote) UnicastRemoteObject.exportObject(connection,0);
					ControllerGUI controllerGUI=clientGUI.showGame();
					ClientView view=new GUI(connection, clientGame, controllerGUI);
					clientGame.registerObserver(view);
					connection.registerObserver(clientController);
					view.welcome(name.getText());
					view.input();
					break;
				}
			} catch (SocketException | RemoteException e) {
					alert.setHeaderText("Wrong address, try again!");
					alert.showAndWait();
					return;
				}
			}
		}
}
