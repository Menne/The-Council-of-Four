package client.controller;


import client.modelDTO.GameDTO;
import client.modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observer;

public class ClientController implements Observer<ClientNotify> {

	private GameDTO clientGame;
	
	public ClientController(GameDTO clientGame) {
		this.clientGame=clientGame;
	}
	
	
	@Override
	public void update(ClientNotify notify) {
		
		notify.updateModel(clientGame);	
		
	}

}
