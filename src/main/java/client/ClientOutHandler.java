package client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import client.clientController.ClientController;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private ClientController clientController;
	
	public ClientOutHandler(ObjectOutputStream socketOut, ClientController clientController) {
		this.socketOut = socketOut;
		this.clientController=clientController;
	}
	

	@Override
	public void run() {
		while (true)
			try {
				if (clientController.isSend()) {
					socketOut.writeObject(clientController.getSelectedAction());
					socketOut.flush();
				}
				clientController.setSend(false);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
