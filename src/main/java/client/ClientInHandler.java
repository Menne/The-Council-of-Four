package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import view.ViewNotify;

public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	
	public ClientInHandler(ObjectInputStream socketIn) {
		this.socketIn=socketIn;
	}

	@Override
	public void run() {
		
		while(true){
			try {
				
				Object object = socketIn.readObject();
				ViewNotify viewNotify=(ViewNotify) object;
				viewNotify.stamp();
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
