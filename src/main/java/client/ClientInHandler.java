package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import client.ModelDTO.GameDTO;
import model.Game;
import view.ViewNotify;

public class ClientInHandler implements Runnable {
	
	private ObjectInputStream socketIn;
	private final GameDTO clientGame;
	
	public ClientInHandler(ObjectInputStream socketIn, GameDTO game) {
		this.socketIn=socketIn;
		this.clientGame=game;
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
