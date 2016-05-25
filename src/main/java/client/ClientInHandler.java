package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import client.controller.ClientController;
import server.view.clientNotifies.ClientNotify;

public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	private ClientController clientController;
	
	public ClientInHandler(ObjectInputStream socketIn, ClientController clientController) {
		this.socketIn=socketIn;
		this.clientController=clientController;
	}

	
	@Override
	public void run() {
		while (true){
			try {
				Object object = socketIn.readObject();
				ClientNotify clientNotify=(ClientNotify) object;
				this.clientController.updateFromIn(clientNotify);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
