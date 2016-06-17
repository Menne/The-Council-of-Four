package client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class ControllerLogin {

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
	
	@FXML
	private TextArea error;
	
	private ClientGUI clientGUI;
	
	
	
	public void setClientGUI(ClientGUI clientGUI) {
		this.clientGUI = clientGUI;
	}



	public void play() throws UnknownHostException, IOException, NotBoundException{
		if(name.getText().isEmpty() || address.getText().isEmpty()){
			error.appendText("Error, try again!");
			return;
		}
		while(true){
			try {
				if(connection.getSelectedToggle().equals(socket)){
					ClientSocket clientSocket=new ClientSocket(address.getText(),name.getText());
					clientSocket.startClient(clientGUI.showGame());
					break;
				}
				else{
					ClientRMI clientRMI=new ClientRMI(address.getText(),name.getText());
					clientRMI.startClient(clientGUI.showGame());
					break;
				}
			} catch (SocketException | RemoteException e) {
					error.setText("Wrong address, try again!");
				}
			}
		}
}
