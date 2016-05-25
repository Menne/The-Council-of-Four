package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import client.clientController.ClientController;

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
				UpdateNotify updatedGame=(UpdateNotify) object;
				clientController.updateGame(updatedGame);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
